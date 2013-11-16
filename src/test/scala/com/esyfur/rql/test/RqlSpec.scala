package com.esyfur.rql.test

import com.esyfur.{rql => r}

class RqlSpec extends UnitSpec with ConnectionAndDatabase {

    protected lazy val dbNameTmp = dbName + "_" + getRndSuffix

    describe("RQL") {
        it("should be able to create a database") {
            val expected = Map("created" -> 1.0)
            val cursor = r.dbCreate(dbNameTmp).run
            assertCursor(expected, cursor)
        }

        it("should be able to get a list of existing databases") {
            val cursor = r.dbList.run
        }

        it("should be able to drop a database") {
            val expected = Map("dropped" -> 1.0)
            val cursor = r.dbDrop(dbNameTmp).run
            assertCursor(expected, cursor)
        }
    }

}
