package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}

import com.esyfur.rql.{StrValue, Term, MethodQuery}

class Pluck(operand: Term) extends MethodQuery(operand) {

    protected val termType = p.Term.TermType.PLUCK
    val st = "pluck"

}

class Without(operand: Term) extends MethodQuery(operand) {

    protected val termType = p.Term.TermType.WITHOUT
    val st = "without"

}

class Merge extends MethodQuery {

    protected val termType = p.Term.TermType.MERGE
    val st = "merge"

}

class Append extends MethodQuery {

    protected val termType = p.Term.TermType.APPEND
    val st = "append"

}

class Prepend extends MethodQuery {

    protected val termType = p.Term.TermType.PREPEND
    val st = "prepend"

}

class Difference extends MethodQuery {

    protected val termType = p.Term.TermType.DIFFERENCE
    val st = "difference"

}

class SetInsert extends MethodQuery {

    protected val termType = p.Term.TermType.SET_INSERT
    val st = "set_insert"

}

class SetUnion extends MethodQuery {

    protected val termType = p.Term.TermType.SET_UNION
    val st = "set_union"

}

class SetIntersection extends MethodQuery {

    protected val termType = p.Term.TermType.SET_INTERSECTION
    val st = "set_intersection"

}

class SetDifference extends MethodQuery {

    protected val termType = p.Term.TermType.SET_DIFFERENCE
    val st = "set_difference"

}

class HasFields extends MethodQuery {

    protected val termType = p.Term.TermType.HAS_FIELDS
    val st = "has_fields"

}

class Match(operand: StrValue, regexp: String) extends MethodQuery(operand, regexp) {

    protected val termType = p.Term.TermType.MATCH
    val st = "match"

}
