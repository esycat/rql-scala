package com.esyfur.rql

import scala.util.matching.Regex

import ast.ops._

trait Value extends Term { self: Value =>

    // Comparison operators
    def eq(that: Value) = new Eq(this, that)
    def ne(that: Value) = new Ne(this, that)
    def lt(that: Value) = new Lt(this, that)
    def le(that: Value) = new Le(this, that)
    def gt(that: Value) = new Gt(this, that)
    def ge(that: Value) = new Ge(this, that)

    def ==(that: Value) = eq(that)
    def !=(that: Value) = ne(that)
    def < (that: Value) = lt(that)
    def <=(that: Value) = le(that)
    def > (that: Value) = gt(that)
    def >=(that: Value) = ge(that)

}

trait BoolValue extends Term { self: BoolValue =>

    // Logical operators
    def not()                = new Not(this)
    def and(that: BoolValue) = new All(this, that)
    def or(that: BoolValue)  = new Any(this, that)

    def !                  = not()
    def &(that: BoolValue) = and(that)
    def |(that: BoolValue) = or(that)

}

trait NumValue extends Term { self: NumValue =>

    // Numeric operators
    def add(that: NumValue) = new Add(this, that)
    def sub(that: NumValue) = new Sub(this, that)
    def mul(that: NumValue) = new Mul(this, that)
    def div(that: NumValue) = new Div(this, that)
    def mod(that: NumValue) = new Mod(this, that)

    def +(that: NumValue) = add(that)
    def -(that: NumValue) = sub(that)
    def *(that: NumValue) = mul(that)
    def /(that: NumValue) = div(that)
    def %(that: NumValue) = mod(that)

}

trait StrValue extends Term { self: StrValue =>

    def matchWith(regexp: String) = new Match(this, regexp)
    def matchWith(regexp: Regex)  = new Match(this, regexp.toString())

}


}
