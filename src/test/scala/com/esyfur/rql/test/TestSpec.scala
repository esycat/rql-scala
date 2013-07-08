package com.esyfur.rql.test

import org.scalatest.{FunSpec, BeforeAndAfter}
import org.scalatest.matchers.ShouldMatchers

import com.esyfur.{rql => r}
import com.esyfur.rql.Connection
import java.net.InetSocketAddress

class TestSpec extends FunSpec with BeforeAndAfter with ShouldMatchers {

    val host = "devroom"
    val port = r.DEFAULT_PORT_DRIVER
    val db   = "lalala"
    val tbl  = "awesomeThings"

    describe("The driver") {
        def checkAndClose(connection: Connection): Unit = {
            connection should not be (null)
            connection.close()
            connection.isOpen should be (false)
        }

        it("should be able to establish a connection using a given host and default port, then disconnect") {
            val conn = r.connect(host)
            checkAndClose(conn)
        }

        it("should be able to establish a connection using given host and port, then disconnect") {
            val conn = r.connect(host, port)
            checkAndClose(conn)
        }

        it("should be able to establish a connection using a given socket address, then disconnect") {
            val addr = new InetSocketAddress(host, port)
            val conn = r.connect(addr)
            checkAndClose(conn)
        }
    }

    describe("A connection") {
        it("should be able to use a given database") {
            val conn = r.connect(host, port)
            conn.use(db)
            conn.db.name should be (db)
        }
    }

}
