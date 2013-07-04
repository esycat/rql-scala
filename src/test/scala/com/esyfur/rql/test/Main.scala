package com.esyfur.rql.test

import com.esyfur.{rql => r}

object Main {

    val host = "devroom"
    val db   = "lalala"
    val tbl  = "awesomeThings"

    def main(args: Array[String]) {
        val conn = r.connect(host).repl()
        conn.use(db)

        r.dbList.run

        r.db(db).tableList.run(conn)
        conn.db.tableList.limit(1).run

        //r.db(db).tableCreate(tbl).run()

        r.db(db).table(tbl).count().run()

        println("Yay")

        conn.close()
    }

}
