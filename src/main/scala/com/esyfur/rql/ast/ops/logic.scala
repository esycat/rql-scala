package com.esyfur.rql.ast.ops

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{BoolValue, UnaryQuery, BinaryQuery}

class Any(a: BoolValue, b: BoolValue) extends BinaryQuery(a, b) with BoolValue {

    protected val termType = p.Term.TermType.ANY
    val st = "|"

}

class All(a: BoolValue, b: BoolValue) extends BinaryQuery(a, b) with BoolValue {

    protected val termType = p.Term.TermType.ALL
    val st = "&"

}

class Not(value: BoolValue) extends UnaryQuery(value) with BoolValue {

    protected val termType = p.Term.TermType.NOT
    val st = "~"

}
