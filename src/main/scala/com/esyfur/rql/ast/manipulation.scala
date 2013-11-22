package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core._

private[rql] class Row(attr: String) extends TopLevelQuery(attr) {

    protected val termType = TermType.IMPLICIT_VAR
    val st = "row"

}

private[rql] class Pluck(self: Sequence)
    extends MethodQuery(self)
    with Stream {

    protected val termType = TermType.PLUCK
    val st = "pluck"

}

private[rql] class Without(self: Sequence)
    extends MethodQuery(self)
    with Stream {

    protected val termType = TermType.WITHOUT
    val st = "without"

}

private[rql] class Merge(self: Sequence)
    extends MethodQuery(self)
    with Stream {

    protected val termType = TermType.MERGE
    val st = "merge"

}

private[rql] class Append extends MethodQuery {

    protected val termType = TermType.APPEND
    val st = "append"

}

private[rql] class Prepend extends MethodQuery {

    protected val termType = TermType.PREPEND
    val st = "prepend"

}

private[rql] class Difference extends MethodQuery {

    protected val termType = TermType.DIFFERENCE
    val st = "difference"

}

private[rql] class SetInsert extends MethodQuery {

    protected val termType = TermType.SET_INSERT
    val st = "set_insert"

}

private[rql] class SetUnion extends MethodQuery {

    protected val termType = TermType.SET_UNION
    val st = "set_union"

}

private[rql] class SetIntersection extends MethodQuery {

    protected val termType = TermType.SET_INTERSECTION
    val st = "set_intersection"

}

private[rql] class SetDifference extends MethodQuery {

    protected val termType = TermType.SET_DIFFERENCE
    val st = "set_difference"

}

private[rql] class HasFields extends MethodQuery {

    protected val termType = TermType.HAS_FIELDS
    val st = "has_fields"

}

private[rql] class Match(self: StrValue, regexp: String) extends MethodQuery(self, regexp) {

    protected val termType = TermType.MATCH
    val st = "match"

}
