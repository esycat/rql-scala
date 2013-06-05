package com.esyfur.rql

import java.io._
import java.net.Socket

import rethinkdb.{Ql2 => p}
import com.esyfur.rql.ast.{DbDrop, DbCreate, DbList}

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

        val version = pack(p.VersionDummy.Version.V0_1.getNumber)

        out.write(version)
        //out2.println("asdfasfsdfa")
        println("lalala1")
        println("lalala2")

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

    def execute(q: Query): Response = {
        if (!isOpen) throw new RqlDriverError("Connection is closed.")

        val protobuf = p.Query.newBuilder()
            .setType(p.Query.QueryType.START)
            .setToken(token)
            .setQuery(q.getTerm)

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

    private def send(protobuf: p.Query): Unit = {
        val size = pack(protobuf.getSerializedSize)
        out.write(size)
        protobuf.writeTo(out)

        println("Length 1:")
        println(protobuf.toByteArray.length)

        println("Length 2:")
        println(protobuf.toByteString.size())

        println("Length 3:")
        println(protobuf.getSerializedSize)
    }

    private def receive(): Response = {
        val reader  = new BufferedReader(new InputStreamReader(in))
        val writer = new PrintWriter(out, true)

        writer.println(" ")

        println("Any luck?")
        println(in.read())
        println(reader.readLine())

        new Response
    }

}
