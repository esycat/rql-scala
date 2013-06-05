package com.esyfur

import java.nio.{ByteBuffer,ByteOrder}

import rethinkdb.{Ql2 => p}

package object rql {

    val DEFAULT_HOST = "localhost"
    val DEFAULT_PORT_DRIVER = 28015
    val DEAFULT_PORT_CLUSTER = 29015
    val DEFAULT_PORT_WEB = 8080

    def connect(host: String = DEFAULT_HOST, port: Int = DEFAULT_PORT_DRIVER, db: String = null): Connection = {
        val conn = new Connection(host, port)
        if (db != null) conn.use(db)
        conn
    }

    def pack(value: Int): Array[Byte] = {
        val bytes = ByteBuffer.allocate(4)
            .order(ByteOrder.LITTLE_ENDIAN)
            .putInt(p.VersionDummy.Version.V0_1.getNumber)
            .array()

        println("[pack] before: " + value)
        println("[pack] after:  " + bytes)
        println("[pack] string: " + new String(bytes))

        //new String(bytes)

        bytes
    }

}
