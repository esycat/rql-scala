package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{Term, MethodQuery}

object Table {

    def apply(name: String) = new Table(name)

}

class Table(val name: String) extends Term {

    protected val termType = p.Term.TermType.TABLE

    def create(): TableCreate = {
        new TableCreate(name)
    }

    def drop(): TableDrop = {
        new TableDrop(name)
    }

    private def index(name: String) = new Index(name)

    def indexCreate(name: String) = index(name).create()

    def indexDrop(name: String) = index(name).create()

    def indexList(): IndexList = {
        new IndexList
    }

    def insert() = {

    }

    def update() = {

    }

    def replace() = {

    }

    def delete() = {

    }

}

class TableCreate(val name: String) extends MethodQuery(name) {
    protected val termType = p.Term.TermType.TABLE_CREATE
    val st = "table_create"
}

class TableDrop(val name: String) extends MethodQuery {
    protected val termType = p.Term.TermType.TABLE_DROP
    val st = "table_drop"
}

class TableList extends MethodQuery {
    protected val termType = p.Term.TermType.TABLE_LIST
    val st = "table_list"
}
