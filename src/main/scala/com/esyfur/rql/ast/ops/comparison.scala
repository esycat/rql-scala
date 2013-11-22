package com.esyfur.rql.ast.ops

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core._

private[rql] class Eq(a: Value, b: Value) extends BinaryQuery(a, b) with Value {

    protected val termType = TermType.EQ
    val st = "=="

}

private[rql] class Ne(a: Value, b: Value) extends BinaryQuery(a, b) {

    protected val termType = TermType.NE
    val st = "!="

}

private[rql] class Lt(a: Value, b: Value) extends BinaryQuery(a, b) {

    protected val termType = TermType.LT
    val st = "<"

}

private[rql] class Le(a: Value, b: Value) extends BinaryQuery(a, b) {

    protected val termType = TermType.LE
    val st = "<="

}

private[rql] class Gt(a: Value, b: Value) extends BinaryQuery(a, b) {

    protected val termType = TermType.GT
    val st = ">"

}

private[rql] class Ge(a: Value, b: Value) extends BinaryQuery(a, b) {

    protected val termType = TermType.GE
    val st = ">="

}
