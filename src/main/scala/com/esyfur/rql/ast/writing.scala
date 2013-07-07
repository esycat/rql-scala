package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{Table, MethodQuery}

class Insert[T](val table: Table, json: collection.Map[String, T]) extends MethodQuery(table, json) {

    protected val termType = p.Term.TermType.INSERT
    val st = "insert"

}

class Update[T](val table: Table, json: collection.Map[String, T]) extends MethodQuery(table, json) {

    protected val termType = p.Term.TermType.UPDATE
    val st = "update"

}

class Replace(val table: Table) extends MethodQuery(table) {

    protected val termType = p.Term.TermType.REPLACE
    val st = "replace"

}

class Delete(val table: Table) extends MethodQuery(table) {

    protected val termType = p.Term.TermType.DELETE
    val st = "delete"

}
