package com.esyfur.rql

import java.io.{InputStream, OutputStream, IOException}
import java.nio.{ByteOrder, ByteBuffer}
import java.net.{InetSocketAddress, Socket}
import java.util.concurrent.atomic.AtomicInteger

import scala.collection.JavaConverters._

import com.google.protobuf.Message
import com.rethinkdb.{Ql2 => p}

import Connection.{pack, unpack, console}

object Connection {
    private[rql] var default: Connection = _

    /**
     * Packs given integer value into a little-endian byte array.
     */
    private def pack(value: Int): Array[Byte] = {
        ByteBuffer.allocate(4)
            .order(ByteOrder.LITTLE_ENDIAN)
            .putInt(value)
            .array()
    }

    /**
     * Unpacks given little-endian byte array into an integer value.
     */
    private def unpack(value: Array[Byte]): Int = {
        ByteBuffer.wrap(value)
            .order(ByteOrder.LITTLE_ENDIAN)
            .getInt()
    }

    /**
     * A helper to print byte arrays to stdout.
     */
    private def console(value: Array[Byte]): Unit = {
        val tpl = "[%d] %s"
        //println(tpl.format(value.length, value.mkString(" ")))
    }

    private def console(values: Array[Byte]*): Unit = {
        values foreach console
    }

}

class Connection(
    private val address: InetSocketAddress
    ) extends AutoCloseable {

    private val token: AtomicInteger = new AtomicInteger()

    private var socket: Socket = _
    private var in: InputStream = _
    private var out: OutputStream = _

    private var defaultDb: Option[Db] = None

    reconnect()

    def reconnect(): Connection = {
        if (isOpen) close()

        try {
            socket = new Socket(address.getAddress, address.getPort)
            socket.setKeepAlive(true)
            socket.setTcpNoDelay(true)

            in  = socket.getInputStream()
            out = socket.getOutputStream()
        }
        catch {
            case e: IOException => {
                val message = "Could not connect to %s.".format(address)
                throw new RqlDriverError(message)
            }
        }

        val version = pack(p.VersionDummy.Version.V0_1.getNumber)
        console(version)
        out.write(version)

        this
    }

    def close(): Unit = {
        if (!isOpen) throw new RqlDriverError("Connection is closed.")

        in.close()
        out.close()
        socket.close()
    }

    def isOpen: Boolean = {
        socket != null && !socket.isClosed && socket.isConnected
    }

    def repl(): Connection = {
        Connection.default = this
        this
    }

    def use(name: String): Connection = {
        this.defaultDb = Some(Db(name))
        this
    }

    def db = this.defaultDb.get

    def execute[T](query: Query, options: QueryOptions): Cursor = {
        if (!isOpen) throw new RqlDriverError("Connection is closed.")

        // Constructing query.
        val builder = p.Query.newBuilder()
            .setType(p.Query.QueryType.START)
            .setToken(token.incrementAndGet())
            .setQuery(query.build)

        defaultDb foreach { db => builder.addGlobalOptargs(
            p.Query.AssocPair.newBuilder()
                .setKey("db")
                .setVal(db.build)
            )
        }

        val message = builder.build()

        send(message)
        receive(message)
    }

    private[rql] def continue(token: Long): Unit = {
        val builder = p.Query.newBuilder()
            .setType(p.Query.QueryType.CONTINUE)
            .setToken(token)

        val message = builder.build()

        send(message)
    }

    private[rql] def terminate(token: Long): Unit = {
        val builder = p.Query.newBuilder()
            .setType(p.Query.QueryType.STOP)
            .setToken(token)

        val message = builder.build()

        send(message)
    }

    private def send(message: Message): Unit = {
        val size = pack(message.getSerializedSize)

        out.write(size)
        message.writeTo(out)

        out.flush()
    }

    private def receive[T](query: p.Query): Cursor = {
        val size = unpack(read())

        val buffer = read(size)
        console(buffer)

        val response = p.Response.parseFrom(buffer)

        // Check that this is the response we were expecting.
        if (response.getToken() != query.getToken())
            // This response is corrupted or not intended for us.
            throw new RqlDriverError("Unexpected response received.")

        import p.Response.ResponseType.{SUCCESS_ATOM, SUCCESS_PARTIAL, SUCCESS_SEQUENCE, RUNTIME_ERROR, COMPILE_ERROR, CLIENT_ERROR}
        response.getType() match {
            // Atom response
            case SUCCESS_ATOM => {
                val data = Datum unwrap response.getResponse(0)
                new Cursor(this, response, data)
            }

            // Sequence or partial responses
            case SUCCESS_PARTIAL | SUCCESS_SEQUENCE => {
                val data = response.getResponseList().asScala.map(Datum unwrap _)
                new Cursor(this, response, data)
            }

            // Error responses
            case RUNTIME_ERROR => {
                throw new RqlRuntimeError(response.getResponse(0).getRStr)
            }
            case COMPILE_ERROR => {
                throw new RqlCompileError(response.getResponse(0).getRStr)
            }
            case CLIENT_ERROR => {
                throw new RqlClientError(response.getResponse(0).getRStr)
            }

            // Unknown response
            case responseType => {
                val message = "Unknown response type (%s).".format(responseType)
                throw new RqlDriverError(message)
            }
        }
    }

    private def read(length: Int = 4): Array[Byte] = {
        val buffer = new Array[Byte](length)
        val readLength = in.read(buffer)

        if (readLength < buffer.length) {
            close()
            throw new RqlDriverError("Could not read from the socket. Disconnected.")
        }

        buffer
    }

}
