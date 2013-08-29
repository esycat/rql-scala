package com.esyfur.rql.test

import org.scalatest.{FunSpec, BeforeAndAfterAll}
import org.scalatest.matchers.ShouldMatchers
import com.esyfur.rql.Cursor

abstract class BaseSpec extends FunSpec with BeforeAndAfterAll with ShouldMatchers {

    protected def getRndSuffix = scala.util.Random.alphanumeric.take(5).mkString.toLowerCase

    protected def assertCursor(expected: Any)(actual: Cursor): Unit = {
        assert(actual.hasNext)
        assertResult(expected)(actual.next())
    }

}
