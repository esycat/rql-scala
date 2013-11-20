package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core.{MethodQuery, Query, Term}

private[rql] class Map extends MethodQuery {

    protected val termType = TermType.MAP
    val st = "map"

}

private[rql] class WithFields extends MethodQuery {

    protected val termType = TermType.WITH_FIELDS
    val st = "with_fields"

}

private[rql] class ConcatMap extends MethodQuery {

    protected val termType = TermType.CONCATMAP
    val st = "concat_map"

}

private[rql] class Skip(operand: Term, number: Integer) extends MethodQuery(operand, number) {

    protected val termType = TermType.SKIP
    val st = "skip"
}

private[rql] class Slice(operand: Term, startIndex: Int, endIndex: Int) extends MethodQuery(operand, startIndex, endIndex) {

    protected val termType = TermType.SLICE

}

private[rql] class IsEmpty(operand: Term) extends MethodQuery(operand) {

    protected val termType = TermType.IS_EMPTY

}

private[rql] class Union(operand: Term, query: Query) extends MethodQuery(operand, query) {

    protected val termType = TermType.UNION
    val st = "union"

}

private[rql] class Sample(operand: Term, number: Int) extends MethodQuery(operand, number) {

    protected val termType = TermType.UNION
    val st = "sample"

}
