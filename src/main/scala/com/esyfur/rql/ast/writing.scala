package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.MethodQuery

class Insert extends MethodQuery {

    protected val termType = p.Term.TermType.INSERT
    val st = "insert"

}

class Update extends MethodQuery {

    protected val termType = p.Term.TermType.UPDATE
    val st = "update"

}

class Replace extends MethodQuery {

    protected val termType = p.Term.TermType.REPLACE
    val st = "replace"

}

class Delete extends MethodQuery {

    protected val termType = p.Term.TermType.DELETE
    val st = "delete"

}
