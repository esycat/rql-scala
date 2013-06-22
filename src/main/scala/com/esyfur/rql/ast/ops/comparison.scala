package com.esyfur.rql.ast.ops

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{BiOpQuery, Query}

class Eq(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.EQ
    val st = "=="

}

class Ne(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.NE
    val st = "!="

}

class Lt(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.LT
    val st = "<"

}

class Le(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.LE
    val st = "<="

}

class Gt(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.GT
    val st = ">"

}

class Ge(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.GE
    val st = ">="

}
