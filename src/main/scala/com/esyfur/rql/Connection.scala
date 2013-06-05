package com.esyfur.rql

import java.io._
import java.net.Socket

import com.google.protobuf.Message
import rethinkdb.{Ql2 => p}
import ast.{DbDrop, DbCreate, DbList}

object Connection {
    var default: Connection = _
}

class Connection(
    private val host: String,
    private val port: Int
    ) extends AutoCloseable {

    private var socket: Socket = _
    private var in: InputStream = _
    private var out: OutputStream = _

    private var db: Db = _

    private var nextToken: Int = 1

    reconnect()

    def reconnect(): Connection = {
        if (isOpen) close()

        socket = new Socket(host, port)
        in  = socket.getInputStream()
        out = socket.getOutputStream()
        //out = System.out

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
        this.db = new Db(name)
        this
    }

    def execute(query: Query, options: Map[String, String]): Response = {
        if (!isOpen) throw new RqlDriverError("Connection is closed.")

        // Constructing query.
        val protobuf = p.Query.newBuilder()
            .setType(p.Query.QueryType.START)
            .setToken(token)
            .setQuery(query.build)

        send(protobuf.build)
        receive()
    }

    private def token: Int = {
        val token = nextToken
        nextToken += 1
        token
    }

    def dbList() = new DbList

    /*
    def dbCreate(name: String) = new DbCreate

    def dbDrop() = new DbDrop
    */

    private def send(protobuf: Message): Unit = {
        val size = pack(protobuf.getSerializedSize)

        console(size, protobuf.toByteArray)

        out.write(size)
        protobuf.writeTo(out)

        /*
        println("Length 1:")
        println(protobuf.toByteArray.length)

        println("Length 2:")
        println(protobuf.toByteString.size())

        println("Length 3:")
        println(protobuf.getSerializedSize)
        */
    }

    private def receive(): Response = {
        val reader = new BufferedReader(new InputStreamReader(in))
        val writer = new PrintWriter(out, true)

        println("Any luck?:")
        println(in.read())

        new Response
    }

    private def console(value: Array[Byte]): Unit = {
        val tpl = "[%d] %s"
        println(tpl.format(value.length, value.mkString(" ")))
    }

    private def console(values: Array[Byte]*): Unit = {
        values.foreach(console)
    }

}
