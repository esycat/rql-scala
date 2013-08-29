package com.esyfur.rql.test

import com.esyfur.{rql => r}
import com.esyfur.rql.Connection
import java.net.InetSocketAddress

class DriverSpec extends BaseSpec with SuppliedConfig {

    private def assertConnection(connection: Connection): Unit = {
        connection should not be (null)
        connection.isOpen should be (true)
        connection.close()
    }

    describe("The driver") {
        it("should be able to establish a connection using a given host and default port") {
            val connection = r.connect(dbHost)
            assertConnection(connection)
        }

        it("should be able to establish a connection using given host and port") {
            val connection = r.connect(dbHost, dbPort)
            assertConnection(connection)
        }

        it("should be able to establish a connection using a provided socket address") {
            val addr = new InetSocketAddress(dbHost, dbPort)
            val connection = r.connect(addr)
            assertConnection(connection)
        }
    }

    describe("A connection") {
        it("should be able to disconnect") {
            val connection = r.connect(dbHost, dbPort)
            connection.close()
            connection.isOpen should be (false)
        }

        it("should be able to set itself as default") {
            val connection = r.connect(dbHost, dbPort)
            connection.repl()
            Connection.default should be (connection)
            connection.close()
        }

        it("should be able to use a given database as default") {
            val connection = r.connect(dbHost, dbPort)
            connection.use(dbName)
            connection.db.name should be (dbName)
            connection.close()
        }
    }

}
