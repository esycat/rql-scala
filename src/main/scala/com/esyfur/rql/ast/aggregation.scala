package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{Term, MethodQuery}

class Distinct(operand: Term) extends MethodQuery(operand) {

    protected val termType = p.Term.TermType.DISTINCT
    val st = "distinct"

}

class Reduce(operand: Term) extends MethodQuery(operand) {

    protected val termType = p.Term.TermType.REDUCE
    val st = "reduce"

}

class Count(operand: Term) extends MethodQuery(operand) {

    protected val termType = p.Term.TermType.COUNT
    val st = "count"

}

class Limit(operand: Term, limit: Int) extends MethodQuery(operand /*, limit*/) {

    protected val termType = p.Term.TermType.LIMIT
    val st = "limit"

}

class Contains(operand: Term) extends MethodQuery(operand) {

    protected val termType = p.Term.TermType.CONTAINS
    val st = "contains"
}
