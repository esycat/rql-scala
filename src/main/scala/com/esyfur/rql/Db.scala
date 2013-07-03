package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}

object Db {

    def apply(name: String) = new Db(name)

}

class Db(val name: String) extends MethodQuery(name) {

    protected val termType = p.Term.TermType.DB
    val st = "db"

    protected override val posArgs = Seq(Datum(name))

    def create(): DbCreate = new DbCreate(name)

    def drop(): DbDrop = new DbDrop(name)

    def table(name: String) = new Table(this, name)

    def tableCreate(name: String) = table(name).create()

    def tableDrop(name: String) = table(name).drop()

    def tableList = new TableList

}

class DbCreate(val name: String) extends MethodQuery {
    protected val termType = p.Term.TermType.DB_CREATE
    val st = "db_create"
}

class DbDrop(val name: String) extends MethodQuery {
    protected val termType = p.Term.TermType.DB_DROP
    val st = "db_drop"
}

class DbList extends MethodQuery {
    protected val termType = p.Term.TermType.DB_LIST
    val st = "db_list"
}
