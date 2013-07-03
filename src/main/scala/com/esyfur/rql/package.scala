package com.esyfur

import java.net.InetSocketAddress

package object rql {

    val DEFAULT_HOST         = "localhost"
    val DEFAULT_PORT_DRIVER  = 28015
    val DEAFULT_PORT_CLUSTER = 29015
    val DEFAULT_PORT_WEB     = 8080

    def connect(host: String = DEFAULT_HOST, port: Int = DEFAULT_PORT_DRIVER, db: String = null): Connection = {
        val addr = new InetSocketAddress(host, port)
        connect(addr, db)
    }

    // def connect(host: String, db: String): Connection = connect(host = host, db = db)

    def connect(address: InetSocketAddress, db: String): Connection = {
        val conn = new Connection(address)
        if (db != null) conn.use(db)
        conn
    }

    def db(name: String) = Db(name)

    def dbCreate(name: String) = db(name).create()

    def dbDrop(name: String) = db(name).drop()

    def dbList = new DbList

    def table(name: String) = Connection.default.db.table(name)

}
