package com.esyfur.rql.ast.ops

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core.{BinaryQuery, Value}

class Eq(a: Value, b: Value) extends BinaryQuery(a, b) with Value {

    protected val termType = TermType.EQ
    val st = "=="

}

class Ne(a: Value, b: Value) extends BinaryQuery(a, b) {

    protected val termType = TermType.NE
    val st = "!="

}

class Lt(a: Value, b: Value) extends BinaryQuery(a, b) {

    protected val termType = TermType.LT
    val st = "<"

}

class Le(a: Value, b: Value) extends BinaryQuery(a, b) {

    protected val termType = TermType.LE
    val st = "<="

}

class Gt(a: Value, b: Value) extends BinaryQuery(a, b) {

    protected val termType = TermType.GT
    val st = ">"

}

class Ge(a: Value, b: Value) extends BinaryQuery(a, b) {

    protected val termType = TermType.GE
    val st = ">="

}
