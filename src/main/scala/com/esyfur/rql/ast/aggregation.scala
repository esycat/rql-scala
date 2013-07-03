package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{MethodQuery}

class Distinct extends MethodQuery {

    protected val termType = p.Term.TermType.DISTINCT
    val st = "distinct"

}

class Reduce extends MethodQuery {

    protected val termType = p.Term.TermType.REDUCE
    val st = "reduce"

}

class Count extends MethodQuery {

    protected val termType = p.Term.TermType.COUNT
    val st = "count"

}

class Limit extends MethodQuery {

    protected val termType = p.Term.TermType.LIMIT
    val st = "limit"

}

class Contains extends MethodQuery {

    protected val termType = p.Term.TermType.CONTAINS
    val st = "contains"
}
