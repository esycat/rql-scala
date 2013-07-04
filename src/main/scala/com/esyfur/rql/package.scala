package com.esyfur

import java.net.InetSocketAddress
import com.esyfur.rql.ast.{Ordering, Asc, Desc}

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

    def expr[T](value: T): Term = value match {
        case v: Term => v
        case _ => Datum(value)
    }

    def branch(test: Boolean, positive: (() => Unit), negative: (() => Unit)) = ???

    def js(expression: String) = ???

    def sum(key: String) = ???

    def avg(key: String) = ???

    def asc(key: String): Ordering = new Asc(key)

    def desc(key: String): Ordering = new Desc(key)

    def db(name: String) = Db(name)

    def dbCreate(name: String) = db(name).create()

    def dbDrop(name: String) = db(name).drop()

    def dbList = new DbList

    def table(name: String) = Connection.default.db.table(name)

}
