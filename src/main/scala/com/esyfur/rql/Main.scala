package com.esyfur.rql

import java.io.{PrintWriter, InputStreamReader, BufferedReader}
import java.net.Socket

import com.google.protobuf.GeneratedMessage

object Main {

    var conn: Connection = _
    var host = "devroom"

    def main(args: Array[String]) {
        conn = new Connection(host)

        println("Yay")

        conn.close()
    }

}
