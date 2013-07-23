package com.esyfur.rql.test

import com.esyfur.{rql => r}
import com.esyfur.rql.Connection
import java.net.InetSocketAddress

class DriverSpec extends BaseSpec with SuppliedConfig {

    describe("The driver") {
        it("should be able to establish a connection using a given host and default port") {
            val connection = r.connect(dbHost)
            connection should not be (null)
            connection.close()
        }

        it("should be able to establish a connection using given host and port") {
            val connection = r.connect(dbHost, dbPort)
            connection should not be (null)
            connection.close()
        }

        it("should be able to establish a connection using a given socket address") {
            val addr = new InetSocketAddress(dbHost, dbPort)
            val connection = r.connect(addr)
            connection should not be (null)
            connection.close()
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
