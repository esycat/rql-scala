package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{Table, MethodQuery}

class InnerJoin(table: Table, predicate: Predicate) extends MethodQuery {

    protected val termType = p.Term.TermType.INNER_JOIN
    val st = "inner_join"

}

class OuterJoin(table: Table, predicate: Predicate) extends MethodQuery {

    protected val termType = p.Term.TermType.OUTER_JOIN
    val st = "outer_join"

}

class EqJoin(leftAttr: String, table: Table, index: Option[String] = None) extends MethodQuery {

    protected val termType = p.Term.TermType.EQ_JOIN
    val st = "eq_join"

}

class Zip extends MethodQuery {

    protected val termType = p.Term.TermType.ZIP
    val st = "zip"

}
