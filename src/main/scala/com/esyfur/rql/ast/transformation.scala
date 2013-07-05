package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{Term, Query, MethodQuery}

class Map extends MethodQuery {

    protected val termType = p.Term.TermType.MAP
    val st = "map"

}

class WithFields extends MethodQuery {

    protected val termType = p.Term.TermType.WITH_FIELDS
    val st = "with_fields"

}

class ConcatMap extends MethodQuery {

    protected val termType = p.Term.TermType.CONCATMAP
    val st = "concat_map"

}

class Skip(operand: Term, number: Integer) extends MethodQuery(operand, number) {

    protected val termType = p.Term.TermType.SKIP
    val st = "skip"
}

class Slice(operand: Term, startIndex: Int, endIndex: Int) extends MethodQuery(operand, startIndex, endIndex) {

    protected val termType = p.Term.TermType.SLICE

}

class Union(operand: Term, query: Query) extends MethodQuery(operand, query) {

    protected val termType = p.Term.TermType.UNION
    val st = "union"

}
