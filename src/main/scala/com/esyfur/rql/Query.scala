package com.esyfur.rql

import rethinkdb.{Ql2 => p}

abstract class Query {

    val termType: p.Term.TermType

    var state: String = _

    override def toString = {
        val printer = new QueryPrinter(this)
        printer.print()
    }

    final def run(): Response = {
        if (Connection.default == null)
            throw new RqlDriverError("Query.run must be given a connection to run on.")

        run(Connection.default)
    }

    final def run(conn: Connection): Response = {
        conn.execute(this)
    }

    def getTerm(): p.Term = {
        val protobuf = p.Term.newBuilder()
            .setType(termType)

        protobuf.build
    }

}

abstract class BiOpQuery extends Query {

}

abstract class TopLevelQuery extends Query {

}

abstract class MethodQuery extends Query {

}

abstract class Datum extends Query {

    var datumType: p.Datum.DatumType = _

}
