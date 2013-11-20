package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core.{MethodQuery, Term}

class Reduce(operand: Term) extends MethodQuery(operand) {

    protected val termType = TermType.REDUCE
    val st = "reduce"

}

class Distinct(operand: Term) extends MethodQuery(operand) {

    protected val termType = TermType.DISTINCT
    val st = "distinct"

}

class Count(operand: Term) extends MethodQuery(operand) {

    protected val termType = TermType.COUNT
    val st = "count"

}

class Limit(operand: Term, number: Int) extends MethodQuery(operand, number) {

    protected val termType = TermType.LIMIT
    val st = "limit"

}

class Contains(operand: Term, values: Any*) extends MethodQuery(operand) {

    protected val termType = TermType.CONTAINS
    val st = "contains"

}
