package com.esyfur.rql.test

import com.esyfur.{rql => r}
import com.esyfur.rql.Connection
import java.net.InetSocketAddress

class RqlSpec extends BaseSpec with ConnectionAndDatabase {

    private lazy val name: String = configMap("db.name").asInstanceOf[String]

    describe("RQL") {
        it("should be able to create a database") {
            val cursor = r.dbCreate(name).run
        }

        it("should be able to get a list of existing databases") {
            val cursor = r.dbList.run
        }

        it("should be able to drop a database") {
            val cursor = r.dbDrop(name).run
        }
    }

}
