package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.core.{MethodQuery, Term}

class Get(key: String) extends MethodQuery(key) {

    protected val termType = p.Term.TermType.GET
    val st = "get"

}

class GetAll(key: String, index: Option[String] = None) extends MethodQuery(key) {

    protected val termType = p.Term.TermType.GET_ALL
    val st = "get_all"

}

class Between(operand: Term, lowerKey: String, upperKey: String, index: Option[String] = None)
    extends MethodQuery(operand, lowerKey, upperKey) {

    protected val termType = p.Term.TermType.BETWEEN
    val st = "between"

}

class Filter(operand: Term, predicate: Predicate) extends MethodQuery(operand, predicate) {

    protected val termType = p.Term.TermType.FILTER
    val st = "filter"

}
