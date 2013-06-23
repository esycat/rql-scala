package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}

import com.esyfur.rql.MethodQuery

class Append extends MethodQuery {

    val termType = p.Term.TermType.APPEND
    val st = "append"

}

class Prepend extends MethodQuery {

    val termType = p.Term.TermType.PREPEND
    val st = "prepend"

}

class Difference extends MethodQuery {

    val termType = p.Term.TermType.DIFFERENCE
    val st = "difference"

}
