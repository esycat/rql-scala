package com.esyfur.rql.test

import com.esyfur.{rql => r}

object Main {

    val host = "devroom"
    val db   = "lalala"

    def main(args: Array[String]) {
        val conn = r.connect(host).repl()
        conn.use(db)

        r.dbList.run
        conn.database.get.tableList.run

        println("Yay")

        conn.close()
    }

}
