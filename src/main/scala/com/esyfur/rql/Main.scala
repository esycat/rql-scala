package com.esyfur.rql

import rethinkdb.{Ql2 => p}

object Main {

    var conn: Connection = _
    var host = "devroom"

    def main(args: Array[String]) {
        conn = connect(host).repl()

        conn.dbList.run

        println("Yay")

        conn.close()
    }

}
