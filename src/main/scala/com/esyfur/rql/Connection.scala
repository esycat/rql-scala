package com.esyfur.rql

import java.io._
import java.net.Socket

import rethinkdb.{Ql2 => rethinkdb}

object Connection {
    var default: Connection = _
}

class Connection extends AutoCloseable {

    private var host: String = DEFAULT_HOST
    private var port: Int = DEFAULT_PORT_DRIVER

    private var socket: Socket = _
    private var in: BufferedReader = _
    private var out: PrintWriter = _

    private var db: Db = _

    def this(host: String = DEFAULT_HOST, port: Int = DEFAULT_PORT_DRIVER, db: String = null) = {
        this()

        this.host = host
        this.port = port
        if (db != null) use(db)

        connect()
    }

    private def connect(): Unit = {
        if (isOpen) throw new RqlError("Already connected")

        socket = new Socket(host, port)
        in  = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        out = new PrintWriter(socket.getOutputStream(), true)

        println(getVersion)
        sendStr(getVersion)

        println(in.readLine())
    }

    def reconnect() = {
        if (isOpen) close()
        connect()
    }

    def close(): Unit = {
        if (!isOpen) throw new RqlClientError("Not connected")

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

    private def sendStr(s: String): Unit = {
        out.write(s)
    }

    private def sendProtobuf(): Unit = {

    }

}
