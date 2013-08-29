package com.esyfur.rql

import scala.collection.{Seq, Map, mutable}

import com.rethinkdb.{Ql2 => p}

abstract class Query extends Term with Operators with Sequence with Selection {

    protected val posArgs: Seq[Term] = Seq.empty[Term]
    protected val optArgs: Map[String, Term] = Map.empty[String, Term]

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

    final def run(conn: Connection, options: Option[QueryOptions] = None): Cursor = {
        conn.execute(this, options.getOrElse(QueryOptions()))
    }

}

/**
 * Base class for all arithmetic, bitwise, comparison, logic and other operators.
 */
abstract class OpQuery extends Query {

}

/**
 * Base class for unary operators.
 */
abstract class UnOpQuery(operand: Term) extends OpQuery {

    protected override val posArgs = Seq(operand)

}

/**
 * Base class for binary operators.
 */
abstract class BiOpQuery(a: Term, b: Term) extends OpQuery {

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
