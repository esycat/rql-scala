package com.esyfur.rql.ast.ops

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core.{BinaryQuery, NumValue}

class Add(a: NumValue, b: NumValue) extends BinaryQuery(a, b) with NumValue {

    protected val termType = TermType.ADD
    val st = "+"

}

class Sub(a: NumValue, b: NumValue) extends BinaryQuery(a, b) with NumValue {

    protected val termType = TermType.SUB
    val st = "-"

}

class Mul(a: NumValue, b: NumValue) extends BinaryQuery(a, b) with NumValue {

    protected val termType = TermType.MUL
    val st = "*"

}

class Div(a: NumValue, b: NumValue) extends BinaryQuery(a, b) with NumValue {

    protected val termType = TermType.DIV
    val st = "/"

}

class Mod(a: NumValue, b: NumValue) extends BinaryQuery(a, b) with NumValue {

    protected val termType = TermType.MOD
    val st = "%"

}
