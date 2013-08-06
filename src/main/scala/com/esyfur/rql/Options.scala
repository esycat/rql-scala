package com.esyfur.rql

trait Options {}

case class TableOptions(
    durability: Option[Durability.Value] = None,
    primaryKey: Option[String] = None,
    cacheSize: Option[Int] = None,
    dataCenter: Option[String] = None
    ) extends Options

case class QueryOptions(
    useOutdated: Option[Boolean] = None,
    noreply: Option[Boolean] = None
    ) extends Options

case class InsertOptions(
    durability: Option[Durability.Value] = None,
    returnVals: Option[Boolean] = None,
    upsert: Option[Boolean] = None
    ) extends Options

case class UpdateOptions(
    durability: Option[Durability.Value] = None,
    returnVals: Option[Boolean] = None
    ) extends Options


object Durability extends Enumeration {
    val Hard = Value("hard")
    val Soft = Value("soft")
}
