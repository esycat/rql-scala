package com.esyfur.rql

import ast.ops._

trait Operators { self: Query =>

    // Comparison operators
    def ==(that: Query) = new Eq(this, that)
    def !=(that: Query) = new Ne(this, that)
    def < (that: Query) = new Lt(this, that)
    def <=(that: Query) = new Le(this, that)
    def > (that: Query) = new Gt(this, that)
    def >=(that: Query) = new Ge(this, that)

    // Numeric operators
    def !              = new Not(this)
    def +(that: Query) = new Add(this, that)
    def -(that: Query) = new Sub(this, that)
    def *(that: Query) = new Mul(this, that)
    def /(that: Query) = new Div(this, that)
    def %(that: Query) = new Mod(this, that)
    def &(that: Query) = new All(this, that)
    def |(that: Query) = new Any(this, that)

}
