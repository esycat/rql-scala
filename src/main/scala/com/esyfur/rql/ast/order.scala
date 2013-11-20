package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core.{MethodQuery, ValueQuery, Term}

private[rql] class OrderBy(operand: Term, keys: String*) extends MethodQuery(operand /*, keys: _* */) {

    protected val termType = TermType.ORDERBY

}

abstract class Ordering(val key: String) extends ValueQuery(key)

private[rql] class Asc(key: String) extends Ordering(key) {

    protected val termType = TermType.ASC
    val st = "asc"

}

private[rql] class Desc(key: String) extends Ordering(key) {

    protected val termType = TermType.DESC
    val st = "desc"

}
