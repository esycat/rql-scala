package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}

protected class Index(val name: String) extends TopLevelQuery {

    protected val termType = null

    def create(): IndexCreate = {
        new IndexCreate
    }

    def drop(): IndexDrop = {
        new IndexDrop
    }

}

class IndexList extends MethodQuery {
    protected val termType = p.Term.TermType.INDEX_LIST
}

class IndexCreate extends MethodQuery {
    protected val termType = p.Term.TermType.INDEX_CREATE
}

class IndexDrop extends MethodQuery {
    protected val termType = p.Term.TermType.INDEX_DROP
}
