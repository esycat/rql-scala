package com.esyfur.rql

import scala.util.Random.nextDouble
import com.esyfur.{rql => r}

object Main extends App {

    val host = "devroom"
    val db   = "lalala"
    val tbl  = "awesomeThings"

    val data1 = Map("rndVal" -> nextDouble, "title" -> "Master of Magic", "year" -> 1994, "genre" -> "strategy", "altNames" -> Seq("MoM"))
    val data2 = Map("rndVal" -> nextDouble, "title" -> "Master of Orion", "year" -> 1993, "genre" -> "strategy", "altNames" -> Seq("MoO"))

    var conn: Connection = _
    var c: Cursor = _

    conn = r.connect(host).repl().use(db)
    setUp()
    exercise()
    tearDown()
    conn.close()

    private def exercise(): Unit = {
        c = r.db(db).table(tbl).limit(2).run()
        print(c)

        c = r.db(db).table(tbl).slice(1, 2).run()
        print(c)

        c = r.db(db).table(tbl).count().run
        print(c)

        c = r.db(db).table(tbl).isEmpty.run()
        print(c)
    }

    private def setUp(): Unit = {
        c = r.dbCreate(db).run()
        print(c)

        c = r.dbList.run()
        print(c)

        c = r.db(db).tableCreate(tbl).run()
        print(c)

        c = r.db(db).tableList.run()
        print(c)

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
        println(c.chunk)
    }

}
