package com.esyfur

import java.nio.{ByteBuffer,ByteOrder}

import rethinkdb.{Ql2 => rethinkdb}

package object rql {

    final val DEFAULT_HOST = "localhost"
    final val DEFAULT_PORT_DRIVER = 28015
    final val DEAFULT_PORT_CLUSTER = 29015
    final val DEFAULT_PORT_WEB = 8080

    final def getVersion = {
        val buf = ByteBuffer.allocate(4)
            .order(ByteOrder.LITTLE_ENDIAN)
            .putInt(rethinkdb.VersionDummy.Version.V0_1.getNumber)
            .array()

        new String(buf)
    }

}
