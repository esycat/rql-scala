package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{Datum, TopLevelQuery}

object Db {

}

class Db(val name: String) extends TopLevelQuery {

    protected val termType = p.Term.TermType.DB
    val st = "db"

    protected override val posArgs = Seq(Datum(name))

    def create(): DbCreate = {
        new DbCreate
    }

    def drop(): DbDrop = {
        new DbDrop
    }

    def table(name: String) = new Table(name)

    def tableCreate(name: String) = table(name).create()

    def tableDrop(name: String) = table(name).drop()

    def tableList() = new TableList

}

class DbList extends TopLevelQuery {
    protected val termType = p.Term.TermType.DB_LIST
    val st = "db_list"
}

class DbCreate extends TopLevelQuery {
    protected val termType = p.Term.TermType.DB_CREATE
    val st = "db_create"
}

class DbDrop extends TopLevelQuery {
    protected val termType = p.Term.TermType.DB_DROP
    val st = "db_drop"
}
