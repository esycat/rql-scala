package com.esyfur.rql.toplevel

import com.esyfur.rql._
import com.esyfur.rql.ast._

private[rql] trait Funcs {

    def db(name: String): Db = Db(name)

    def dbCreate(name: String): DbCreate = db(name).create()

    def dbDrop(name: String): DbDrop = db(name).drop()

    def dbList: DbList = new DbList

    def table(name: String): Table = Connection.default.db.table(name)

    def row(attr: String): Row = new Row(attr)

    def count(): Aggregator = CountBy()

    def sum(attr: String): Aggregator = Sum(attr)

    def avg(attr: String): Aggregator = Avg(attr)

    def asc(attr: String): Ordering = new Asc(attr)

    def desc(attr: String): Ordering = new Desc(attr)

    def funCall(value: Predicate) = new FunCall(value)

    def branch(test: Boolean, positive: (() => Unit), negative: (() => Unit)) = ???

    def foreach() = ???

    def error(message: String) = new UserError(message)

    def js(jsString: String) = new JavaScript(jsString)

    def json(jsonString: String) = new JSON(jsonString)

}
