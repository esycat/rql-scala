package com.esyfur.rql

import java.io.{InputStream, OutputStream, IOException}
import java.nio.{ByteOrder, ByteBuffer}
import java.net.Socket

import com.google.protobuf.Message
import rethinkdb.{Ql2 => p}

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
    private def console(value: Array[Byte]) {
        val tpl = "[%d] %s"
        println(tpl.format(value.length, value.mkString(" ")))
    }

    private def console(values: Array[Byte]*) {
        values.foreach(console)
    }

}

class Connection(
    private val host: String,
    private val port: Int
    ) extends AutoCloseable {

    private var socket: Socket = _
    private var in: InputStream = _
    private var out: OutputStream = _

    private var db: Db = _

    private var nextToken: Long = 1

    reconnect()

    def reconnect(): Connection = {
        if (isOpen) close()

        try {
            socket = new Socket(host, port)
            in  = socket.getInputStream()
            out = socket.getOutputStream()
        }
        catch {
            case e: IOException => throw new RqlDriverError("Could not connect to %s:%d.".format(host, port))
        }

        val version = pack(p.VersionDummy.Version.V0_1.getNumber)
        console(version)
        out.write(version)

        this
    }

    def close() {
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
        this.db = new Db(name)
        this
    }

    def execute(query: Query, options: Map[String, String]): Cursor = {
        if (!isOpen) throw new RqlDriverError("Connection is closed.")

        val token = yieldToken
        val term = query.build

        // Constructing query.
        val message = p.Query.newBuilder()
            .setType(p.Query.QueryType.START)
            .setToken(token)
            .setQuery(term)
            .build()

        send(message)
        receive(message)
    }

    private def yieldToken: Long = {
        val token = nextToken
        nextToken += 1
        token
    }

    private def send(message: Message) {
        val size = pack(message.getSerializedSize)

        println("sending data...")
        out.write(size)
        message.writeTo(out)
    }

    private def receive(query: p.Query): Cursor = {
        println("receiving data...")

        val size = unpack(read())

        println("parsing results...")

        val buffer = read(size)
        console(buffer)

        val response = p.Response.parseFrom(buffer)
        println(response)

        // Check that this is the response we were expecting.
        if (response.getToken() != query.getToken())
            // This response is corrupted or not intended for us.
            throw new RqlDriverError("Unexpected response received.")

        import p.Response.ResponseType
        response.getType() match {
            // Error responses
            case ResponseType.RUNTIME_ERROR => {
                val message = "" // response.getResponse(0)
                throw new RqlRuntimeError(message)
            }
            case ResponseType.COMPILE_ERROR => {
                val message = "" // response.getResponse(0)
                throw new RqlCompileError(message)
            }
            case ResponseType.CLIENT_ERROR => {
                val message = "" // response.getResponse(0)
                throw new RqlClientError(message)
            }

            // Sequence responses
            case ResponseType.SUCCESS_PARTIAL | ResponseType.SUCCESS_SEQUENCE => {
                val chunk = "" // TODO
                new Cursor(this, query, response, chunk)
            }

            // Atom response
            case ResponseType.SUCCESS_ATOM => {
                val chunk = "" // TODO
                new Cursor(this, query, response, chunk)
            }

            // Unknown response
            case responseType => {
                throw new RqlDriverError("Unknown response type %s.".format(responseType))
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
