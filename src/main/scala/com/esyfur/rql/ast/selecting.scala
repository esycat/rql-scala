package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.MethodQuery

class Get(key: String) extends MethodQuery(key) {

    protected val termType = p.Term.TermType.GET
    val st = "get"

}

class GetAll(key: String, index: Option[String] = None) extends MethodQuery(key) {

    protected val termType = p.Term.TermType.GET_ALL
    val st = "get_all"

}

class Between(lowerKey: String, upperKey: String, index: Option[String] = None) extends MethodQuery(lowerKey, upperKey) {

    protected val termType = p.Term.TermType.BETWEEN
    val st = "between"

}

class Filter(predicate: Predicate) extends MethodQuery {

    protected val termType = p.Term.TermType.FILTER
    val st = "filter"

}
