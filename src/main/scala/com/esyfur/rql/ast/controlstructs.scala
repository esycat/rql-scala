package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.core.{TopLevelQuery, MethodQuery, ValueQuery, Query}

class Var extends Query {

    protected val termType = p.Term.TermType.VAR

}

class ImplicitVar extends Query {

    protected val termType = p.Term.TermType.IMPLICIT_VAR

}

class JavaScript(expression: String) extends ValueQuery(expression) {

    protected val termType = p.Term.TermType.JAVASCRIPT
    val st = "js"

}

class UserError extends TopLevelQuery {

    protected val termType = p.Term.TermType.ERROR
    val st = "error"

}

class Default extends Query {

    protected val termType = p.Term.TermType.DEFAULT
    val st = "default"

}

class GetField extends Query {

    protected val termType = p.Term.TermType.GET_FIELD
}

class Keys extends MethodQuery {

    protected val termType = p.Term.TermType.KEYS
    val st = "keys"

}

class Predicate extends MethodQuery {

    protected val termType = ???

}
