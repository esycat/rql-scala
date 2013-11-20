package com.esyfur.rql.toplevel

import com.esyfur.rql.{Connection, DbList, Db}
import com.esyfur.rql.ast.{Desc, Asc, Ordering}

private[rql] trait Func {

    def funcall() = ???

    def branch(test: Boolean, positive: (() => Unit), negative: (() => Unit)) = ???

    def foreach() = ???

    def error() = ???

    def default() = ???

    def js(expression: String) = ???

    def count() = ???

    def sum(key: String) = ???

    def avg(key: String) = ???

    def asc(key: String): Ordering = new Asc(key)

    def desc(key: String): Ordering = new Desc(key)

    def db(name: String) = Db(name)

    def dbCreate(name: String) = db(name).create()

    def dbDrop(name: String) = db(name).drop()

    def dbList = new DbList

    def table(name: String) = Connection.default.db.table(name)

}
