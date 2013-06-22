package com.esyfur.rql

import scala.collection.mutable.ListMap

import com.rethinkdb.{Ql2 => p}
import ast.ops._

abstract class Query extends Term {

    override def toString = {
        val printer = new QueryPrinter(this)
        printer.print()
    }

    final def run(): Cursor = {
        if (Connection.default == null)
            throw new RqlDriverError("Query.run must be given a connection to run on.")

        run(Connection.default)
    }

    final def run(conn: Connection): Cursor = {
        val options = ListMap[String, String]()
        conn.execute(this, options.toMap)
    }

    /* The following are all operators and methods that operate on Rql queries to build up more complex operations.
     */

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

abstract class BiOpQuery(a: Query, b: Query) extends Query {

}

abstract class TopLevelQuery extends Query {}

abstract class MethodQuery extends Query {}
