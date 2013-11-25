package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core._

private[rql] trait Joins extends Term { self: Sequence =>

    def innerJoin(other: Sequence, predicate: JoinPredicate): Stream = new InnerJoin(this, other, predicate)

    def outerJoin(other: Sequence, predicate: JoinPredicate): Stream = new OuterJoin(this, other, predicate)

    def eqJoin(leftAttr: String, other: Sequence, index: String = "id"): Stream = new EqJoin(this, leftAttr, other, index)

}

final class InnerJoin(left: Sequence, right: Sequence, predicate: JoinPredicate)
    extends MethodQuery(left, right, predicate)
    with Stream {

    protected val termType = TermType.INNER_JOIN
    val st = "innerJoin"

}

final class OuterJoin(left: Sequence, right: Sequence, predicate: JoinPredicate)
    extends MethodQuery(left, right, predicate)
    with Stream {

    protected val termType = TermType.OUTER_JOIN
    val st = "outerJoin"

}

final class EqJoin(left: Sequence, leftAttr: String, right: Sequence, index: String)
    extends MethodQuery(left, leftAttr, right, index)
    with Stream {

    protected val termType = TermType.EQ_JOIN
    val st = "eqJoin"

}

final class Zip(self: Stream) extends MethodQuery(self) with Stream {

    protected val termType = TermType.ZIP
    val st = "zip"

}
