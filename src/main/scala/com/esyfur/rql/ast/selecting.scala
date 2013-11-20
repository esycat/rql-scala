package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core.{MethodQuery, Term}

private[rql] class Get(key: String) extends MethodQuery(key) {

    protected val termType = TermType.GET
    val st = "get"

}

private[rql] class GetAll(key: String, index: Option[String] = None) extends MethodQuery(key) {

    protected val termType = TermType.GET_ALL
    val st = "get_all"

}

private[rql] class Between(operand: Term, lowerKey: String, upperKey: String, index: Option[String] = None)
    extends MethodQuery(operand, lowerKey, upperKey) {

    protected val termType = TermType.BETWEEN
    val st = "between"

}

private[rql] class Filter(operand: Term, predicate: Predicate) extends MethodQuery(operand, predicate) {

    protected val termType = TermType.FILTER
    val st = "filter"

}
