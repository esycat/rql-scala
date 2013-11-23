package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core._

private[rql] class Map(self: Sequence) extends MethodQuery(self) with Stream {

    protected val termType = TermType.MAP
    val st = "map"

}

private[rql] class WithFields(self: Sequence) extends MethodQuery(self) with Stream {

    protected val termType = TermType.WITH_FIELDS
    val st = "with_fields"

}

private[rql] class ConcatMap(self: Sequence) extends MethodQuery(self) with Stream {

    protected val termType = TermType.CONCATMAP
    val st = "concat_map"

}

private[rql] class Skip(self: Sequence, n: Int) extends MethodQuery(self, n) with Stream {

    protected val termType = TermType.SKIP
    val st = "skip"
}

private[rql] class Limit(self: Sequence, n: Int) extends MethodQuery(self, n) with Stream {

    protected val termType = TermType.LIMIT
    val st = "limit"

}

private[rql] class Slice(self: Sequence, startIndex: Int, endIndex: Int)
    extends MethodQuery(self, startIndex, endIndex)
    with Stream {

    protected val termType = TermType.SLICE

}

private[rql] class Nth(self: Sequence, index: Int) extends MethodQuery(self, index) {

    protected val termType = TermType.NTH
    val st = "nth"
}

private[rql] class IsEmpty(self: Sequence) extends MethodQuery(self) with BoolValue {

    protected val termType = TermType.IS_EMPTY

}

private[rql] class Union(self: Sequence, other: Sequence) extends MethodQuery(self, other) {

    protected val termType = TermType.UNION
    val st = "union"

}

private[rql] class Sample(self: Sequence, n: Int) extends MethodQuery(self, n) with Selection {

    protected val termType = TermType.UNION
    val st = "sample"

}
