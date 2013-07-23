package com.esyfur.rql.test

import org.scalatest.{FunSpec, BeforeAndAfterAll}
import org.scalatest.matchers.ShouldMatchers

abstract class BaseSpec extends FunSpec with BeforeAndAfterAll with ShouldMatchers {

    protected def getRndSuffix = scala.util.Random.alphanumeric.take(5).mkString.toLowerCase

}
