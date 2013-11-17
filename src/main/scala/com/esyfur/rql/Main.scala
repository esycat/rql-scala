package com.esyfur.rql

import scala.util.Random.nextDouble
import com.esyfur.{rql => r}
import com.esyfur.rql.core.TimeValue

object Main extends App {

    val host = "devroom"
    val db   = "awesomeness"
    val tbl  = "beautifulThings"

    val data1 = Map("rndVal" -> nextDouble, "title" -> "Master of Magic", "year" -> 1994, "genre" -> "strategy", "altNames" -> Seq("MoM"))
    val data2 = Map("rndVal" -> nextDouble, "title" -> "Master of Orion", "year" -> 1993, "genre" -> "strategy", "altNames" -> Seq("MoO"))

    var c: Cursor = _

    // connect to the server, set default connection and database
    val conn = r.connect(host).repl().use(db)

//    setUp()
//    exercise()
    exerciseDateTime()
//    tearDown()

    conn.close()

    private def exercise(): Unit = {
        c = r.expr(5).add(2).add(10).sub(12).mul(6).div(3).mod(3).run()
        print(c)

        c = (r.expr(5) * r.expr(5)).run()
        print(c)

        // select data from the table in different ways
        c = r.db(db).table(tbl).limit(2).run()
        print(c)

        c = r.db(db).table(tbl).slice(1, 2).run()
        print(c)

        // check whether the table is empty
        c = r.db(db).table(tbl).count().run
        print(c)

        c = r.db(db).table(tbl).isEmpty.run()
        print(c)
    }

    private def exerciseDateTime(): Unit = {
        c = r.now.run()
        print(c)

        c = r.epochTime(System.currentTimeMillis() / 1000L).run()
        print(c)

        c = r.iso8601()
        print(c)

        c = r.time(1983, 11, 13, "+10:00").run
        print(c)
    }

    private def setUp(): Unit = {
        // create a new database and show a list of existing databases
        c = r.dbCreate(db).run()
        print(c)

        c = r.dbList.run()
        print(c)

        // create a new table and show a list of existing tables in the database
        c = r.db(db).tableCreate(tbl).run()
        print(c)

        c = r.db(db).tableList.run()
        print(c)

        // insert some data
        c = r.db(db).table(tbl).insert(data1).run()
        print(c)

        c = r.db(db).table(tbl).insert(data2).run()
        print(c)
    }

    private def tearDown(): Unit = {
        c = r.db(db).tableDrop(tbl).run()
        print(c)

        c = r.dbDrop(db).run()
        print(c)
    }

    private def print(c: Cursor): Unit = {
        if (c.hasNext) println(c.next())
        else println("Cursor empty")
    }

    private def print(t: TimeValue): Unit = {
        println(t.minutes())
    }

}
