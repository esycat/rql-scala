package com.esyfur.rql

import ast.ops._

trait Operators { self: Query =>

    // Comparison operators
    def eq(that: Query) = new Eq(this, that)
    def ne(that: Query) = new Ne(this, that)
    def lt(that: Query) = new Lt(this, that)
    def le(that: Query) = new Le(this, that)
    def gt(that: Query) = new Gt(this, that)
    def ge(that: Query) = new Ge(this, that)

    def ==(that: Query) = eq(that)
    def !=(that: Query) = ne(that)
    def < (that: Query) = lt(that)
    def <=(that: Query) = le(that)
    def > (that: Query) = gt(that)
    def >=(that: Query) = ge(that)

    // Numeric operators
    def add(that: Query) = new Add(this, that)
    def sub(that: Query) = new Sub(this, that)
    def mul(that: Query) = new Mul(this, that)
    def div(that: Query) = new Div(this, that)
    def mod(that: Query) = new Mod(this, that)

    def +(that: Query) = add(that)
    def -(that: Query) = sub(that)
    def *(that: Query) = mul(that)
    def /(that: Query) = div(that)
    def %(that: Query) = mod(that)

    // Logical operators
    def not()            = new Not(this)
    def and(that: Query) = new All(this, that)
    def or(that: Query)  = new Any(this, that)

    def !              = not()
    def &(that: Query) = and(that)
    def |(that: Query) = or(that)

}
