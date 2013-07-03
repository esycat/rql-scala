package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.ast._

object Table {

    def apply(name: String) = new Table(name)

}

class Table(val name: String) extends Term with Sequence {

    protected val termType = p.Term.TermType.TABLE

    def create() = new TableCreate(name)

    def drop() = new TableDrop(name)

    private def index(name: String) = new Index(name)

    def indexCreate(name: String) = index(name).create()

    def indexDrop(name: String) = index(name).create()

    def indexList() = new IndexList

    def insert() = new Insert()

    def update() = new Update()

    def replace() = new Replace()

    def delete() = new Delete()

    def get(key: String) = new Get(key)

    def getAll(key: String, index: Option[String] = None) = new GetAll(key, index)

    def between(lowerKey: String, upperKey: String, index: Option[String] = None) = new Between(lowerKey, upperKey, index)

    def innerJoin(table: Table, predicate: Predicate) = new InnerJoin(table, predicate)

    def outerJoin(table: Table, predicate: Predicate) = new OuterJoin(table, predicate)

    def eqJoin(leftAttr: String, table: Table, index: Option[String] = None) = new EqJoin(leftAttr, table, index)

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
