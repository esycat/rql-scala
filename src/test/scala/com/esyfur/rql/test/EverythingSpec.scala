package com.esyfur.rql.test

import org.scalatest.Suites

class EverythingSpec extends Suites (
    new DriverSpec,
    new RqlSpec,
    new QuerySpec
)
