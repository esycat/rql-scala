package com.esyfur.rql.test

import org.scalatest.{Suite, BeforeAndAfterAll}

trait SuppliedConfig extends Suite with BeforeAndAfterAll {

    protected var configMap: Map[String, Any] = _

    protected override def beforeAll(configMap: Map[String, Any]): Unit = {
        this.configMap = configMap
    }

    protected def dbHost = configMap("db.host").asInstanceOf[String]
    protected def dbPort = configMap("db.port").asInstanceOf[String].toInt
    protected def dbName = configMap("db.name").asInstanceOf[String]

}
