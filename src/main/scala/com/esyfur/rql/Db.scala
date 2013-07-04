package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}

object Db {

    def apply(name: String) = new Db(name)

}

class Db(val name: String) extends TopLevelQuery(name) {

    protected val termType = p.Term.TermType.DB
    val st = "db"

    def create(): DbCreate = new DbCreate(this)

    def drop(): DbDrop = new DbDrop(this)

    def table(name: String) = new Table(this, name)

    def tableCreate(name: String) = table(name).create()

    def tableDrop(name: String) = table(name).drop()

    def tableList = new TableList(this)

}

class DbCreate(val db: Db) extends TopLevelQuery(db.name) {
    protected val termType = p.Term.TermType.DB_CREATE
    val st = "db_create"
}

class DbDrop(val db: Db) extends TopLevelQuery(db.name) {
    protected val termType = p.Term.TermType.DB_DROP
    val st = "db_drop"
}

class DbList extends TopLevelQuery {
    protected val termType = p.Term.TermType.DB_LIST
    val st = "db_list"
}
