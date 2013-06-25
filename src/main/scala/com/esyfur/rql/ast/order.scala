package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.Term

abstract class Order extends Term {

}

class Asc extends Order {

    protected val termType = p.Term.TermType.ASC
    val st = "asc"

}

class Desc extends Order {

    protected val termType = p.Term.TermType.DESC
    val st = "desc"

}
