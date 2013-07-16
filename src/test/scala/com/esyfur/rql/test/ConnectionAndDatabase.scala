package com.esyfur.rql.test

import com.esyfur.{rql => r}
import r.Connection

import org.scalatest.{Suite, BeforeAndAfterAll}

trait ConnectionAndDatabase extends Suite with BeforeAndAfterAll {

    protected var connection: Connection = _

    protected override def beforeAll(configMap: Map[String, Any]): Unit = {
        val host = configMap("db.host").asInstanceOf[String]
        val port = configMap("db.port").asInstanceOf[String].toInt
        val name = configMap("db.name").asInstanceOf[String]

        connection = r.connect(host, port, name)
    }

    protected override def afterAll(): Unit = {
        connection.close()
    }

}
