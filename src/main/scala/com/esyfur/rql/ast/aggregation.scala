package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core._

private[rql] class Reduce(self: Sequence, function: ReductionFunction) extends MethodQuery(self) {

    protected val termType = TermType.REDUCE
    val st = "reduce"

}

private[rql] class Count(self: Sequence) extends MethodQuery(self) with NumValue {

    protected val termType = TermType.COUNT
    val st = "count"

}

private[rql] class Distinct(self: Sequence) extends MethodQuery(self) {

    protected val termType = TermType.DISTINCT
    val st = "distinct"

}

private[rql] class GroupedMapReduce(self: Sequence, grouping: Predicate, mapping: Predicate, reduction: Predicate, base: Predicate)
    extends MethodQuery(self) {

    protected val termType = TermType.GROUPED_MAP_REDUCE
    val st = "grouped_map_reduce"

}

private[rql] class GroupBy(self: Sequence, reduction: Aggregator, selectors: Seq[String]) extends MethodQuery(self, selectors) {

    protected val termType = TermType.GROUPBY
    val st = "group_by"

}

private[rql] class Contains(self: Sequence, values: Any*) extends MethodQuery(self) with BoolValue {

    protected val termType = TermType.CONTAINS
    val st = "contains"

}

class ReductionFunction {}
