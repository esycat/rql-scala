package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}

object Main {

    val host = "devroom"
    val db   = "test"

    var conn: Connection = _

    def main(args: Array[String]) {
        conn = connect(host).repl()
        conn.use(db)

        dbList.run

        println("Yay")

        conn.close()
    }

}
