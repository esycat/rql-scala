package com.esyfur.rql.ast.ops

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core._

private[rql] class Any(a: BoolValue, b: BoolValue) extends BinaryQuery(a, b) with BoolValue {

    protected val termType = TermType.ANY
    val st = "|"

}

private[rql] class All(a: BoolValue, b: BoolValue) extends BinaryQuery(a, b) with BoolValue {

    protected val termType = TermType.ALL
    val st = "&"

}

private[rql] class Not(value: BoolValue) extends UnaryQuery(value) with BoolValue {

    protected val termType = TermType.NOT
    val st = "~"

}
