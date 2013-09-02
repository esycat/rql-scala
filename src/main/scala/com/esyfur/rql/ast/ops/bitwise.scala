package com.esyfur.rql.ast.ops

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{BoolValue, UnOpQuery, BiOpQuery}

class Any(a: BoolValue, b: BoolValue) extends BiOpQuery(a, b) {

    protected val termType = p.Term.TermType.ANY
    val st = "|"

}

class All(a: BoolValue, b: BoolValue) extends BiOpQuery(a, b) {

    protected val termType = p.Term.TermType.ALL
    val st = "&"

}

class Not(value: BoolValue) extends UnOpQuery(value) {

    protected val termType = p.Term.TermType.NOT
    val st = "~"

}
