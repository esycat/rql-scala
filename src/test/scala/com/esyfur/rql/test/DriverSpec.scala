package com.esyfur.rql.test

import com.esyfur.{rql => r}
import com.esyfur.rql.Connection
import java.net.InetSocketAddress

class DriverSpec extends BaseSpec {

    private var host: String = _
    private var port: Int    = _
    private var name: String = _

    protected override def beforeAll(configMap: Map[String, Any]): Unit = {
        host = configMap("db.host").asInstanceOf[String]
        port = configMap("db.port").asInstanceOf[String].toInt
        name = configMap("db.name").asInstanceOf[String]
    }

    describe("The driver") {
        it("should be able to establish a connection using a given host and default port") {
            val connection = r.connect(host)
            connection should not be (null)
            connection.close()
        }

        it("should be able to establish a connection using given host and port") {
            val connection = r.connect(host, port)
            connection should not be (null)
            connection.close()
        }

        it("should be able to establish a connection using a given socket address") {
            val addr = new InetSocketAddress(host, port)
            val connection = r.connect(addr)
            connection should not be (null)
            connection.close()
        }
    }

    describe("A connection") {
        it("should be able to disconnect") {
            val connection = r.connect(host, port)
            connection.close()
            connection.isOpen should be (false)
        }

        it("should be able to set itself as default") {
            val connection = r.connect(host, port)
            connection.repl()
            Connection.default should be (connection)
            connection.close()
        }

        it("should be able to use a given database") {
            val connection = r.connect(host, port)
            connection.use(name)
            connection.db.name should be (name)
            connection.close()
        }
    }

}
