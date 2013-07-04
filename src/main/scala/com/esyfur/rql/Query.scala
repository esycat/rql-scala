package com.esyfur.rql

import scala.collection.{Seq, Map, mutable}

import com.rethinkdb.{Ql2 => p}

abstract class Query extends Term with Sequence with Operators {

    protected val posArgs: Seq[Term] = mutable.ListBuffer[Term]()
    protected val optArgs: Map[String, Term] = mutable.HashMap[String, Term]()

    override def toString = {
        val printer = new QueryPrinter(this)
        printer.print()
    }

    protected override def getTermBuilder() = {
        val builder = super.getTermBuilder()

        //println("Term: %s, Pos args cnt: %d".format(this.getClass, posArgs.length))

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


    // Polymorphic object/sequence operations
    /*

    def without(attrs: List[Query]) = new Without(attrs)

    def do(func) = new FunCall(func_wrap(func), this)

    def default(handler) = new Default(handler)

    def update(func, non_atomic=(), durability=()) = new Update(func_wrap(func), non_atomic = non_atomic, durability = durability)

    def replace(func, non_atomic=(), durability=()) = new Replace(func_wrap(func), non_atomic = non_atomic, durability = durability)

    def delete(durability=()) = new Delete(durability = durability)
    */

}

/**
 * Base class for all arithmetic, bitwise, comparison, logic and other operators.
 */
abstract class OpQuery extends Query {

}

/**
 * Base class for unary operators.
 */
abstract class UnOpQuery(val operand: Term) extends OpQuery {

    protected override val posArgs = Seq(operand)

}

/**
 * Base class for binary operators.
 */
abstract class BiOpQuery(val a: Term, val b: Term) extends OpQuery {

    protected override val posArgs = Seq(a, b)

}

abstract class ValueQuery(value: String) extends Query {

    protected override val posArgs = Seq(expr(value))

}

abstract class MethodQuery(args: Any*) extends Query {

    protected override val posArgs = for (arg <- args if arg != null) yield expr(arg)

}

abstract class TopLevelQuery(value: String = null) extends Query {

    protected override val posArgs = if (value == null) Seq() else Seq(expr(value))

}
