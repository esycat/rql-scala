package com.esyfur

import java.net.InetSocketAddress

import com.esyfur.rql.toplevel._

package object rql extends Expr with Func with Chronology {

    type Document = collection.Map[String, Any]

    val DEFAULT_HOST         = "localhost"
    val DEFAULT_PORT_DRIVER  = 28015
    val DEAFULT_PORT_CLUSTER = 29015
    val DEFAULT_PORT_WEB     = 8080

    def connect(host: String = DEFAULT_HOST, port: Int = DEFAULT_PORT_DRIVER, db: String = null): Connection = {
        val address = new InetSocketAddress(host, port)
        connect(address, db)
    }

    def connect(address: InetSocketAddress): Connection = new Connection(address)

    def connect(address: InetSocketAddress, db: String): Connection =  connect(address).use(db)

}
