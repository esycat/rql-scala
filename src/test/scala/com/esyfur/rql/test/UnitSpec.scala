package com.esyfur.rql.test

import org.scalatest.{FunSpec, Matchers, BeforeAndAfterAll, OptionValues, Inside}
import com.esyfur.rql.Cursor

abstract class UnitSpec extends FunSpec with Matchers with BeforeAndAfterAll with OptionValues with Inside {

    protected def getRndSuffix = scala.util.Random.alphanumeric.take(5).mkString.toLowerCase

    protected def assertCursor(expected: Any, actual: Cursor): Unit = {
        actual.hasNext should be (true)
        actual.next() should be (expected)
    }

}
