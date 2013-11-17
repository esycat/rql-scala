package com.esyfur.rql

import scala.util.Random.nextDouble
import org.joda.time.{DateTime, DateTimeZone}

import com.esyfur.{rql => r}
import com.esyfur.rql.util._

object Main extends App {

    val host = "devroom"
    val db   = "awesomeness"
    val tbl  = "beautifulThings"

    // connect to the server, set default connection and database
    val conn = r.connect(host).repl().use(db)

    var c: Cursor = _
    var d: Document = _

    val data1 = Map("rndVal" -> nextDouble, "title" -> "Master of Magic", "year" -> 1994, "genre" -> "strategy", "altNames" -> Seq("MoM"))
    val data2 = Map("rndVal" -> nextDouble, "title" -> "Master of Orion", "year" -> 1993, "genre" -> "strategy", "altNames" -> Seq("MoO"))

    setUp()
    exercise()
    exerciseDateTime()
    tearDown()

    conn.close()

    private def exercise(): Unit = {
        c = r.expr(5).add(2).add(10).sub(12).mul(6).div(3).mod(3).run()
        printCursor(c)

        c = (r.expr(5) * r.expr(5)).run()
        printCursor(c)

        // select data from the table in different ways
        c = r.db(db).table(tbl).limit(2).run()
        printCursor(c)

        c = r.db(db).table(tbl).slice(1, 2).run()
        printCursor(c)

        // check whether the table is empty
        c = r.db(db).table(tbl).count().run
        printCursor(c)

        c = r.db(db).table(tbl).isEmpty.run()
        printCursor(c)
    }

    private def exerciseDateTime(): Unit = {
        c = r.now.run
        printCursor(c)

        c = r.epochTime(System.currentTimeMillis() / 1000L).run
        printCursor(c)

        c = r.iso8601(currentTime.toString).run
        printCursor(c)

        c = r.time(1983, 11, 13, "+10:00").run
        printCursor(c)

        val timeZone = DateTimeZone.forOffsetHours(10)
        val timestamp = currentTime.withTimeAtStartOfDay
        val expected = timestamp.withZoneRetainFields(timeZone)

        print(timestamp)
        print(expected)
        println("")

        c = r.time(timestamp.getYear, timestamp.getMonthOfYear, timestamp.getDayOfMonth).run
        d = c.next().asInstanceOf[Document]
        println(d("epoch_time").asInstanceOf[Double].toLong)

        c = r.time(
            expected.getYear, expected.getMonthOfYear, expected.getDayOfMonth,
            getTzOffset(expected.getZone)
        ).run
        d = c.next().asInstanceOf[Document]
        println(d("epoch_time").asInstanceOf[Double].toLong)
    }

    private def setUp(): Unit = {
        // create a new database and show a list of existing databases
        c = r.dbCreate(db).run()
        printCursor(c)

        c = r.dbList.run()
        printCursor(c)

        // create a new table and show a list of existing tables in the database
        c = r.db(db).tableCreate(tbl).run()
        printCursor(c)

        c = r.db(db).tableList.run()
        printCursor(c)

        // insert some data
        c = r.db(db).table(tbl).insert(data1).run()
        printCursor(c)

        c = r.db(db).table(tbl).insert(data2).run()
        printCursor(c)
    }

    private def tearDown(): Unit = {
        c = r.db(db).tableDrop(tbl).run()
        printCursor(c)

        c = r.dbDrop(db).run()
        printCursor(c)
    }

    private def print(dateTime: DateTime): Unit = println(dateTime.getMillis / 1000L)
}
