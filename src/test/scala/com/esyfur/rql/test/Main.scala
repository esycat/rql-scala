package com.esyfur.rql.test

import com.esyfur.{rql => r}

object Main {

    val host = "devroom"
    val db   = "lalala"
    val tbl  = "awesomeThings"

    def main(args: Array[String]): Unit = {
        val conn = r.connect(host).repl()
        conn.use(db)

        val c1 = r.dbList.run
        println(c1.chunk)

        val c2 = r.db(db).tableList.run(conn)
        println(c2.chunk)

        val c3 = conn.db.tableList.limit(1).run
        println(c3.chunk)

        //r.db(db).tableCreate(tbl).run()

        val c4 = r.db(db).table(tbl).count().run()
        println(c4.chunk)

        val c5 = r.db(db).table(tbl).run()
        println(c5.chunk)

        val c6 = r.db(db).table(tbl).slice(1, 2).run()
        println(c6.chunk)

        val c7 = r.db(db).table(tbl).isEmpty.run()
        println(c7.chunk)

        conn.close()
    }

}
