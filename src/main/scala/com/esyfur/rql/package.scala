package com.esyfur

import java.net.InetSocketAddress
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

import rql.ast._
import com.esyfur.rql.core._

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

    def expr(value: Boolean): BoolValue = Datum(value)

    def expr(value: Int): NumValue = Datum(value)

    def expr(value: Long): NumValue = Datum(value)

    def expr(value: Float): NumValue = Datum(value)

    def expr(value: Double): NumValue = Datum(value)

    def expr(value: String): StrValue = Datum(value)

    def expr(value: DateTime): TimeValue = time(value)

    def expr(value: java.util.Date): TimeValue = time(value)

    def expr(value: Option[Any]): Datum[Any] = Datum(value)

    def expr(value: Any): Term = value match {
        case v: Term => v
        case _ => Datum(value)
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

    def now(): TimeValue = new Now()

    def time(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int, zone: String): TimeValue = new Time(
        year, month, day, hour, minute, second, zone
    )

    def time(year: Int, month: Int, day: Int, zone: String): TimeValue = new Time(year, month, day, 0, 0, 0, zone)

    def time(dateTime: DateTime): TimeValue = new Time(
        dateTime.getYear, dateTime.getMonthOfYear, dateTime.getDayOfMonth,
        dateTime.getHourOfDay, dateTime.getMinuteOfHour, dateTime.getSecondOfMinute,
        dateTime.getZone.toString
    )

    def time(dateTime: java.util.Date): TimeValue = time(new DateTime(dateTime))

    def epochTime(seconds: Long): TimeValue = new EpochTime(seconds)

    def iso8601(dateString: String, defaultTimezone: Option[String] = None): TimeValue = new ISO8601(dateString)

}
