package com.esyfur

import java.net.InetSocketAddress
import com.esyfur.rql.ast.{Ordering, Asc, Desc, Time}
import java.util.Date

package object rql {

    type Document = collection.Map[String, Any]

    val DEFAULT_HOST         = "localhost"
    val DEFAULT_PORT_DRIVER  = 28015
    val DEAFULT_PORT_CLUSTER = 29015
    val DEFAULT_PORT_WEB     = 8080

    def connect(host: String = DEFAULT_HOST, port: Int = DEFAULT_PORT_DRIVER, db: String = null): Connection = {
        val addr = new InetSocketAddress(host, port)
        connect(addr, db)
    }

    // def connect(host: String, db: String): Connection = connect(host = host, db = db)

    def connect(address: InetSocketAddress): Connection = new Connection(address)

    def connect(address: InetSocketAddress, db: String): Connection =  connect(address).use(db)

    // def do() = ???

    def branch(test: Boolean, positive: (() => Unit), negative: (() => Unit)) = ???

    def foreach() = ???

    def error() = ???

    def default() = ???

    def expr(value: Any): Term = value match {
        case v: Term => v
        case _       => Datum(value)
    }

    def js(expression: String) = ???

    def count() = ???

    def sum(key: String) = ???

    def avg(key: String) = ???

    def asc(key: String): Ordering = new Asc(key)

    def desc(key: String): Ordering = new Desc(key)

    def db(name: String) = Db(name)

    def dbCreate(name: String) = db(name).create()

    def dbDrop(name: String) = db(name).drop()

    def dbList = new DbList

    def table(name: String) = Connection.default.db.table(name)

    def now(): Time = ???

    def time(year: Short, month: Short, day: Short, hour: Short = null, minute: Short = null, second: Short = null): Time = ???

    def time(date: Date) = ???

    // def time(date: DateTime) = ???

    def epochTime(): Time = ???

    def iso8601(): Time = ???

}
