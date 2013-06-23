package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}

object Db {

}

class Db(val name: String) extends TopLevelQuery {

    val termType = p.Term.TermType.DB
    val st = "db"

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
    val termType = p.Term.TermType.DB_LIST
    val st = "db_list"
}

class DbCreate extends TopLevelQuery {
    val termType = p.Term.TermType.DB_CREATE
    val st = "db_create"
}

class DbDrop extends TopLevelQuery {
    val termType = p.Term.TermType.DB_DROP
    val st = "db_drop"
}
