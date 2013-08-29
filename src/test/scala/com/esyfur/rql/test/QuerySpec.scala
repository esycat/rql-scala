package com.esyfur.rql.test

import com.esyfur.{rql => r}
import scala.collection.mutable.ArrayBuffer

class QuerySpec extends BaseSpec with ConnectionAndDatabase {

    private val tableName = "test"

    describe("A database") {
        it("should be able to create a table") {
            val expected = Map("created" -> 1.0)
            val cursor = r.db(dbName).tableCreate(tableName).run
            assertCursor(expected, cursor)
        }

        it("should be able to get a list of existing tables") {
            val expected = ArrayBuffer(tableName)
            val cursor = r.db(dbName).tableList.run
            assertCursor(expected, cursor)
        }

        it("should be able to drop a table") {
            val expected = Map("dropped" -> 1.0)
            val cursor = r.db(dbName).tableDrop(tableName).run
            assertCursor(expected, cursor)
        }
    }

    describe("A table") {

    }

}
