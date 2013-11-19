package com.esyfur.rql.test

import com.esyfur.{rql => r}
import scala.collection.mutable.ArrayBuffer
import com.esyfur.rql.{Durability, TableOptions}

class QuerySpec extends UnitSpec with ConnectionAndDatabase {

    private val tableName = "test"
    private val columnName = "name"

    describe("A database") {
        it("should be able to create a table") {
            val cursor = r.db(dbName).tableCreate(tableName).run
            assertCursor(Expected.created)(cursor)
        }

        it("should be able to list existing tables") {
            val expected = ArrayBuffer(tableName)
            val cursor = r.db(dbName).tableList.run
            assertCursor(expected)(cursor)
        }

        it("should be able to drop a table") {
            val cursor = r.db(dbName).tableDrop(tableName).run
            assertCursor(Expected.dropped)(cursor)
        }

        it("should be able to create a table with options") {
            val options = TableOptions(primaryKey = Some(columnName), durability = Some(Durability.Soft))
            val cursor = r.db(dbName).tableCreate(tableName).run
            assertCursor(Expected.created)(cursor)
        }
    }

    describe("A table") {
        it("should be able to create an index") {
            val cursor = r.db(dbName).table(tableName).indexCreate(columnName).run
            assertCursor(Expected.created)(cursor)
        }

        it("should be able to list existing indexes") {
            val expected = ArrayBuffer(columnName)
            val cursor = r.db(dbName).table(tableName).indexList().run
            assertCursor(expected)(cursor)
        }

        it("should be able to drop an index") {
            val cursor = r.db(dbName).table(tableName).indexDrop(columnName).run
            assertCursor(Expected.dropped)(cursor)
        }
    }

}
