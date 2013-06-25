package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}

object Table {

}

class Table(val name: String) extends Term {

    protected val termType = p.Term.TermType.TABLE

    def create(): TableCreate = {
        new TableCreate
    }

    def drop(): TableDrop = {
        new TableDrop
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

class TableList extends MethodQuery {
    protected val termType = p.Term.TermType.TABLE_LIST
    val st = "table_list"
}

class TableCreate extends MethodQuery {
    protected val termType = p.Term.TermType.TABLE_CREATE
    val st = "table_create"
}

class TableDrop extends MethodQuery {
    protected val termType = p.Term.TermType.TABLE_DROP
    val st = "table_drop"
}
