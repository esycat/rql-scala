package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}

import com.esyfur.rql.core._

object Db {

    def apply(name: String) = new Db(name)

}

private[rql] class Db(val name: String) extends TopLevelQuery(name) {

    protected val termType = p.Term.TermType.DB
    val st = "db"

    def create(): DbCreate = new DbCreate(this)

    def drop(): DbDrop = new DbDrop(this)

    def table(name: String, useOutdated: Boolean = false): Table = new Table(this, name)

    def tableCreate(name: String, options: Option[TableOptions] = None): TableCreate = table(name).create(options)

    def tableDrop(name: String): TableDrop = table(name).drop()

    def tableList: TableList = new TableList(this)

}

private[rql] class DbCreate(val db: Db) extends TopLevelQuery(db.name) {
    protected val termType = p.Term.TermType.DB_CREATE
    val st = "db_create"
}

private[rql] class DbDrop(val db: Db) extends TopLevelQuery(db.name) {
    protected val termType = p.Term.TermType.DB_DROP
    val st = "db_drop"
}

private[rql] class DbList extends TopLevelQuery {
    protected val termType = p.Term.TermType.DB_LIST
    val st = "db_list"
}
