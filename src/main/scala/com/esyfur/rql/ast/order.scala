package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{ValueQuery, Term, TopLevelQuery, MethodQuery}

class OrderBy(operand: Term, keys: String*) extends MethodQuery(operand /*, keys: _* */) {

    protected val termType = p.Term.TermType.ORDERBY

}

abstract class Ordering(val key: String) extends ValueQuery(key)

class Asc(key: String) extends Ordering(key) {

    protected val termType = p.Term.TermType.ASC
    val st = "asc"

}

class Desc(key: String) extends Ordering(key) {

    protected val termType = p.Term.TermType.DESC
    val st = "desc"

}
