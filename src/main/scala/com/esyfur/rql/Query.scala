package com.esyfur.rql

import scala.collection.Map
import scala.collection.mutable

import com.rethinkdb.{Ql2 => p}
import ast.ops._

abstract class Query extends Term {

    val posArgs: Seq[Query] = new mutable.LinkedList[Query]()
    val optArgs: Map[String, Query] = new mutable.HashMap[String, Query]()

    override def toString = {
        val printer = new QueryPrinter(this)
        printer.print()
    }

    def compose(posArgs: Seq[Query], optArgs: Map[String, Query]) = {

    }

    final def run(): Cursor = {
        if (Connection.default == null)
            throw new RqlDriverError("Query.run must be given a connection to run on.")

        run(Connection.default)
    }

    final def run(conn: Connection): Cursor = {
        val options = mutable.HashMap[String, Query]()
        conn.execute(this, options)
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

    // Polymorphic object/sequence operations
    /*
    def pluck(attrs: List[Query]) = new Pluck(attrs)

    def without(attrs: List[Query]) = new Without(attrs)

    def do(func) = new FunCall(func_wrap(func), this)

    def default(handler) = new Default(handler)

    def update(func, non_atomic=(), durability=()) = new Update(func_wrap(func), non_atomic = non_atomic, durability = durability)

    def replace(func, non_atomic=(), durability=()) = new Replace(func_wrap(func), non_atomic = non_atomic, durability = durability)

    def delete(durability=()) = new Delete(durability = durability)
    */

}

abstract class BiOpQuery(a: Query, b: Query) extends Query {

}

abstract class TopLevelQuery extends Query {

    override def compose(posArgs: Seq[Query], optArgs: Map[String, Query]) = {
        /*
        args.extend([name + '= ' + optargs[name] for name in optargs.keys()])
        T('r.', st, '(', T(*(args), intsp = ', '), ')')
        */
    }

}

abstract class MethodQuery extends Query {

    override def compose(posArgs: Seq[Query], optArgs: Map[String, Query]) = {
        /*
        if needs_wrap(this.args[0]) args[0] = T('r.expr(', args[0], ')')

        restargs = args[1:]
        restargs.extend([k+'='+v for k,v in optargs.items()])
        restargs = T(*restargs, intsp = ', ')

        T(args[0], '.', st, '(', restargs, ')')
        */
    }

}
