package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}

object Table {

}

class Table(val name: String) extends Query {

    val termType = p.Term.TermType.TABLE

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
    val termType = p.Term.TermType.TABLE_LIST
}

class TableCreate extends MethodQuery {
    val termType = p.Term.TermType.TABLE_CREATE
}

class TableDrop extends MethodQuery {
    val termType = p.Term.TermType.TABLE_DROP
}
