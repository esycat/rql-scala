package com.esyfur.rql

import scala.collection.Map
import com.esyfur.rql.core.{Datum, Term}

trait Options {

    def toMap: Map[String, Term]

    /**
     * Takes a map of options, removes empty ones, and returns a map of values.
     */
    //protected def collect(options: Map[String, Option[_]]): Map[String, Term] = options.filter(_._2.isDefined).mapValues(v => Datum(v.get))

    protected def collect(options: Map[String, Option[_]]): Map[String, Term] = options collect {
        case (key: String, opt: Some[_]) => (key, Datum(opt.get))
    }

}

case class TableOptions(
    primaryKey: Option[String] = None,
    durability: Option[Durability.Value] = None,
    cacheSize:  Option[Int] = None,
    dataCenter: Option[String] = None
    ) extends Options {

    def toMap = collect(Map(
        "primary_key" -> primaryKey,
        "durability"  -> durability,
        "cache_size"  -> cacheSize,
        "datacenter"  -> dataCenter
    ))

}

case class QueryOptions(
    useOutdated: Option[Boolean] = None,
    noreply:     Option[Boolean] = None
    ) extends Options {

    def toMap = collect(Map(
        "useOutdated" -> useOutdated,
        "noreply"     -> noreply
    ))

}

case class InsertOptions(
    durability: Option[Durability.Value] = None,
    returnVals: Option[Boolean] = None,
    upsert:     Option[Boolean] = None
    ) extends Options {

    def toMap = collect(Map(
        "durability"  -> durability,
        "return_vals" -> returnVals,
        "upsert"      -> upsert
    ))

}

case class UpdateOptions(
    durability: Option[Durability.Value] = None,
    returnVals: Option[Boolean] = None,
    nonAtomic:  Option[Boolean] = None
    ) extends Options {

    def toMap = collect(Map(
        "durability"  -> durability,
        "return_vals" -> returnVals,
        "non_atomic"  -> nonAtomic
    ))

}

case class SpanOptions(
    leftBound:  Option[SpanBound.Value] = None,
    rightBound: Option[SpanBound.Value] = None
    ) extends Options {

    def toMap = collect(Map(
        "left_bound"  -> leftBound,
        "right_bound" -> rightBound
    ))

}

object Durability extends Enumeration {
    val Hard = Value("hard")
    val Soft = Value("soft")
}

object SpanBound extends Enumeration {
    val Open   = Value("open")
    val Closed = Value("closed")
}
