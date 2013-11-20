package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core.{TopLevelQuery, MethodQuery, ValueQuery, Query}

class Var extends Query {

    protected val termType = TermType.VAR

}

class ImplicitVar extends Query {

    protected val termType = TermType.IMPLICIT_VAR

}

class JavaScript(expression: String) extends ValueQuery(expression) {

    protected val termType = TermType.JAVASCRIPT
    val st = "js"

}

class UserError extends TopLevelQuery {

    protected val termType = TermType.ERROR
    val st = "error"

}

class Default extends Query {

    protected val termType = TermType.DEFAULT
    val st = "default"

}

class GetField extends Query {

    protected val termType = TermType.GET_FIELD
}

class Keys extends MethodQuery {

    protected val termType = TermType.KEYS
    val st = "keys"

}

class Predicate extends MethodQuery {

    protected val termType = ???

}
