package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core.{MethodQuery, Term}

private[rql] class Reduce(operand: Term) extends MethodQuery(operand) {

    protected val termType = TermType.REDUCE
    val st = "reduce"

}

private[rql] class Distinct(operand: Term) extends MethodQuery(operand) {

    protected val termType = TermType.DISTINCT
    val st = "distinct"

}

private[rql] class Count(operand: Term) extends MethodQuery(operand) {

    protected val termType = TermType.COUNT
    val st = "count"

}

private[rql] class Limit(operand: Term, number: Int) extends MethodQuery(operand, number) {

    protected val termType = TermType.LIMIT
    val st = "limit"

}

private[rql] class Contains(operand: Term, values: Any*) extends MethodQuery(operand) {

    protected val termType = TermType.CONTAINS
    val st = "contains"

}
