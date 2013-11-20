package com.esyfur.rql.toplevel

import com.esyfur.rql.{Connection, DbList, Db}
import com.esyfur.rql.ast._

private[rql] trait Func {

    def db(name: String) = Db(name)

    def dbCreate(name: String) = db(name).create()

    def dbDrop(name: String) = db(name).drop()

    def dbList = new DbList

    def table(name: String) = Connection.default.db.table(name)

    def row(atr: String) = ???

    def count() = ???

    def sum(key: String) = ???

    def avg(key: String) = ???

    def asc(key: String): Ordering = new Asc(key)

    def desc(key: String): Ordering = new Desc(key)

    def funcall() = ???

    def branch(test: Boolean, positive: (() => Unit), negative: (() => Unit)) = ???

    def foreach() = ???

    def error(message: String) = new UserError(message)

    def js(jsString: String) = new JavaScript(jsString)

    def json(jsonString: String) = new JSON(jsonString)

}
