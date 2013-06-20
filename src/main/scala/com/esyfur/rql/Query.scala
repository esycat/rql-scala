package com.esyfur.rql

import scala.collection.mutable.ListMap

import com.rethinkdb.{Ql2 => p}

abstract class Query {

    val termType: p.Term.TermType

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

    protected def getTermBuilder(): p.Term.Builder = p.Term.newBuilder().setType(termType)

    def build(): p.Term = getTermBuilder().build()

}

abstract class BiOpQuery extends Query {

}

abstract class TopLevelQuery extends Query {

}

abstract class MethodQuery extends Query {

}

