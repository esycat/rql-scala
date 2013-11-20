package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.core.TopLevelQuery

object Db {

    def apply(name: String) = new Db(name)

}

private[rql] class Db(val name: String) extends TopLevelQuery(name) {

    protected val termType = p.Term.TermType.DB
    val st = "db"

    def create() = new DbCreate(this)

    def drop() = new DbDrop(this)

    def table(name: String, useOutdated: Boolean = false) = new Table(this, name)

    def tableCreate(name: String, options: Option[TableOptions] = None) = table(name).create(options)

    def tableDrop(name: String) = table(name).drop()

    def tableList = new TableList(this)

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
