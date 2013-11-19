package com.esyfur.rql.test

import org.scalatest.{FunSpec, Matchers, BeforeAndAfterAll, OptionValues, Inside}

import com.esyfur.rql.Cursor
import com.esyfur.rql.core.Query

abstract class UnitSpec extends FunSpec with Matchers with BeforeAndAfterAll with OptionValues with Inside {

    protected def getRndSuffix = scala.util.Random.alphanumeric.take(5).mkString.toLowerCase

    protected def assertNotEmpty(cursor: Cursor): Unit = {
        cursor.hasNext should be (true)
    }

    protected def assertCursor(expected: Any, cursor: Cursor): Unit = {
        assertNotEmpty(cursor)
        val actual = cursor.next()
        actual should equal (expected)
    }

    protected def assertQuery(expected: Any)(query: Query): Unit = {
        val cursor = query.run
        assertCursor(expected, cursor)
    }

}
