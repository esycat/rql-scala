package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core.{MethodQuery, StrValue, Term}

private[rql] class Pluck(operand: Term) extends MethodQuery(operand) {

    protected val termType = TermType.PLUCK
    val st = "pluck"

}

private[rql] class Without(operand: Term) extends MethodQuery(operand) {

    protected val termType = TermType.WITHOUT
    val st = "without"

}

private[rql] class Merge extends MethodQuery {

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

private[rql] class Match(operand: StrValue, regexp: String) extends MethodQuery(operand, regexp) {

    protected val termType = TermType.MATCH
    val st = "match"

}
