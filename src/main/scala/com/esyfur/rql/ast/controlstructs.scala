package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core.{TopLevelQuery, MethodQuery, ValueQuery, Query}

private[rql] class Var extends Query {

    protected val termType = TermType.VAR

}

private[rql] class ImplicitVar extends Query {

    protected val termType = TermType.IMPLICIT_VAR

}

private[rql] class JavaScript(jsString: String) extends ValueQuery(jsString) {

    protected val termType = TermType.JAVASCRIPT
    val st = "js"

}

private[rql] class JSON(jsonString: String) extends ValueQuery(jsonString) {

    protected val termType = TermType.JSON
    val st = "js"

}

private[rql] class UserError(message: String) extends TopLevelQuery(message) {

    protected val termType = TermType.ERROR
    val st = "error"

}

private[rql] class Default extends Query {

    protected val termType = TermType.DEFAULT
    val st = "default"

}

private[rql] class GetField extends Query {

    protected val termType = TermType.GET_FIELD
}

private[rql] class Keys extends MethodQuery {

    protected val termType = TermType.KEYS
    val st = "keys"

}

private[rql] class Predicate extends MethodQuery {

    protected val termType = ???

}
