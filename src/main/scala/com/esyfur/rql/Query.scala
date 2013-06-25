package com.esyfur.rql

import scala.collection.{Seq, Map, mutable}

import com.rethinkdb.{Ql2 => p}
import ast.ops._

abstract class Query extends Term {

    protected val posArgs: Seq[Term] = mutable.ListBuffer[Term]()
    protected val optArgs: Map[String, Term] = mutable.HashMap[String, Term]()

    override def toString = {
        val printer = new QueryPrinter(this)
        printer.print()
    }

    protected override def getTermBuilder() = {
        val builder = super.getTermBuilder()

        // applying positional arguments
        for (arg <- posArgs) builder.addArgs(arg.build)

        // applying named arguments
        for ((key, arg) <- optArgs) {
            val pair = p.Term.AssocPair.newBuilder
                .setKey(key)
                .setVal(arg.build)

            builder.addOptargs(pair)
        }

        builder
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

abstract class OpQuery extends Query {

}

abstract class BiOpQuery(a: Term, b: Term) extends OpQuery {

    protected override val posArgs = Seq(a, b)

}

abstract class TopLevelQuery extends Query {

}

abstract class MethodQuery extends Query {

}
