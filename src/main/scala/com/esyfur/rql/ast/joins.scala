package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{Term, Sequence, MethodQuery}

private[rql] trait Joins extends Term { self: Sequence =>

    def innerJoin(other: Sequence, predicate: Predicate) = new InnerJoin(this, other, predicate)

    def outerJoin(other: Sequence, predicate: Predicate) = new OuterJoin(this, other, predicate)

    def eqJoin(attr: String, other: Sequence, index: Option[String] = None) = new EqJoin(this, other, attr, index)

    def zip = ???

}

final class InnerJoin(left: Sequence, right: Sequence, predicate: Predicate) extends MethodQuery {

    protected val termType = p.Term.TermType.INNER_JOIN
    val st = "inner_join"

}

final class OuterJoin(left: Sequence, right: Sequence, predicate: Predicate) extends MethodQuery {

    protected val termType = p.Term.TermType.OUTER_JOIN
    val st = "outer_join"

}

final class EqJoin(left: Sequence, right: Sequence, attr: String, index: Option[String] = None) extends MethodQuery {

    protected val termType = p.Term.TermType.EQ_JOIN
    val st = "eq_join"

}

final class Zip extends MethodQuery {

    protected val termType = p.Term.TermType.ZIP
    val st = "zip"

}
