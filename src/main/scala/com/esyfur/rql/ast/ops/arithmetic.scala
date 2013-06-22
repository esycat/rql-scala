package com.esyfur.rql.ast.ops

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{BiOpQuery, Query}

class Add(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.ADD
    val st = "+"

}

class Sub(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.SUB
    val st = "-"

}

class Mul(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.MUL
    val st = "*"

}

class Div(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.DIV
    val st = "/"

}

class Mod(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.MOD
    val st = "%"

}
