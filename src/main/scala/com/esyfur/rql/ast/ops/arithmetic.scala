package com.esyfur.rql.ast.ops

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{NumValue, BiOpQuery}

class Add(a: NumValue, b: NumValue) extends BiOpQuery(a, b) {

    protected val termType = p.Term.TermType.ADD
    val st = "+"

}

class Sub(a: NumValue, b: NumValue) extends BiOpQuery(a, b) {

    protected val termType = p.Term.TermType.SUB
    val st = "-"

}

class Mul(a: NumValue, b: NumValue) extends BiOpQuery(a, b) {

    protected val termType = p.Term.TermType.MUL
    val st = "*"

}

class Div(a: NumValue, b: NumValue) extends BiOpQuery(a, b) {

    protected val termType = p.Term.TermType.DIV
    val st = "/"

}

class Mod(a: NumValue, b: NumValue) extends BiOpQuery(a, b) {

    protected val termType = p.Term.TermType.MOD
    val st = "%"

}
