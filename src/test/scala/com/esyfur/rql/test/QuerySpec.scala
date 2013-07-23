package com.esyfur.rql.test

import com.esyfur.{rql => r}

class QuerySpec extends BaseSpec with ConnectionAndDatabase {

    private val tableName = "test"

    describe("A database") {
        it("should be able to create a table") {
            val cursor = r.db(dbName).tableCreate(tableName).run
        }

        it("should be able to get a list of existing tables") {
            val cursor = r.db(dbName).tableList.run
        }

        it("should be able to drop a table") {
            val cursor = r.db(dbName).tableDrop(tableName).run
        }
    }

    describe("A table") {

    }

}
