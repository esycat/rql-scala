package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.ast._

object Table {

    def apply(name: String) = new Table(Connection.default.db, name)

}

class Table(val db: Db, val name: String) extends MethodQuery(db, name) with Sequence {

    protected val termType = p.Term.TermType.TABLE

    def create() = new TableCreate(this)

    def drop() = new TableDrop(this)

    private def index(name: String) = new Index(this, name)

    def indexCreate(name: String) = index(name).create()

    def indexDrop(name: String) = index(name).create()

    def indexList() = new IndexList(this)

    def insert() = new Insert(this)

    def update() = new Update(this)

    def replace() = new Replace(this)

    def delete() = new Delete(this)

    def get(key: String) = new Get(key)

    def getAll(key: String, index: Option[String] = None) = new GetAll(key, index)

    def between(lowerKey: String, upperKey: String, index: Option[String] = None) = new Between(lowerKey, upperKey, index)

    def innerJoin(table: Table, predicate: Predicate) = new InnerJoin(table, predicate)

    def outerJoin(table: Table, predicate: Predicate) = new OuterJoin(table, predicate)

    def eqJoin(leftAttr: String, table: Table, index: Option[String] = None) = new EqJoin(leftAttr, table, index)

}

class TableCreate(val table: Table) extends MethodQuery(table.name) {
    protected val termType = p.Term.TermType.TABLE_CREATE
    val st = "table_create"
}

class TableDrop(val table: Table) extends MethodQuery(table.name) {
    protected val termType = p.Term.TermType.TABLE_DROP
    val st = "table_drop"
}

class TableList(val db: Db) extends MethodQuery(db) {
    protected val termType = p.Term.TermType.TABLE_LIST
    val st = "table_list"
}
