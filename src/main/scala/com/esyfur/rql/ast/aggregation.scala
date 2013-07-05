package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{Term, ValueQuery, MethodQuery}

class Reduce(operand: Term) extends MethodQuery(operand) {

    protected val termType = p.Term.TermType.REDUCE
    val st = "reduce"

}

class Distinct(operand: Term) extends MethodQuery(operand) {

    protected val termType = p.Term.TermType.DISTINCT
    val st = "distinct"

}

class Count(operand: Term) extends MethodQuery(operand) {

    protected val termType = p.Term.TermType.COUNT
    val st = "count"

}

class Limit(operand: Term, number: Int) extends MethodQuery(operand, number) {

    protected val termType = p.Term.TermType.LIMIT
    val st = "limit"

}

class Contains(operand: Term, values: Any*) extends MethodQuery(operand) {

    protected val termType = p.Term.TermType.CONTAINS
    val st = "contains"

}
