package com.esyfur.rql.test

import org.scalatest.{Suite, ConfigMap, BeforeAndAfterAll}

trait SuppliedConfig extends Suite with BeforeAndAfterAll {

    protected var config: ConfigMap = _

    protected override def beforeAll(configMap: ConfigMap): Unit = {
        this.config = configMap
    }

    protected def dbHost = config.getRequired[String]("db.host")
    protected def dbPort = config.getRequired[String]("db.port").toInt
    protected def dbName = config.getRequired[String]("db.name")

}
