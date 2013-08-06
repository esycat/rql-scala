package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.ast._

object Table {

    def apply(name: String) = new Table(Connection.default.db, name)

}

class Table(val db: Db, val name: String)
    extends MethodQuery(db, name) with Sequence with Selection {

    protected val termType = p.Term.TermType.TABLE

    def create(): TableCreate = new TableCreate(this)

    def drop(): TableDrop = new TableDrop(this)

    private def index(name: String) = new Index(this, name)

    def indexCreate(name: String): IndexCreate = index(name).create()

    def indexDrop(name: String): IndexDrop = index(name).drop()

    def indexList(): IndexList = new IndexList(this)

    def insert(document: Document, options: Option[InsertOptions] = None) = new Insert(this, document)

    def update(document: Document, options: Option[UpdateOptions] = None) = new Update(this, document)

    def replace(document: Document, options: Option[UpdateOptions] = None) = new Replace(this)

    def delete(): Delete = new Delete(this)

    def get(key: String) = new Get(key)

    def getAll(key: String, index: Option[String] = None) = new GetAll(key, index)

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
