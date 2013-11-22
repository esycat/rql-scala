package com.esyfur.rql.ast

private[rql] abstract sealed class Aggregator

private[rql] case class CountBy() extends Aggregator {

    val value = Map("COUNT" -> true)

}

private[rql] case class Sum(attr: String) extends Aggregator {

    val value = Map("SUM" -> attr)

}

private[rql] case class Avg(attr: String) extends Aggregator {

    val value = Map("AVG" -> attr)

}
