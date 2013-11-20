package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.core.MethodQuery

protected class Index(val table: Table, val name: String) extends MethodQuery(table, name) {

    protected val termType = null

    def create(): IndexCreate = new IndexCreate(this)

    def drop(): IndexDrop = new IndexDrop(this)

}

private[rql] class IndexCreate(val index: Index) extends MethodQuery(index.table, index.name) {
    protected val termType = p.Term.TermType.INDEX_CREATE
}

private[rql] class IndexDrop(val index: Index) extends MethodQuery(index.table, index.name) {
    protected val termType = p.Term.TermType.INDEX_DROP
}

private[rql] class IndexList(val table: Table) extends MethodQuery(table) {
    protected val termType = p.Term.TermType.INDEX_LIST
}
