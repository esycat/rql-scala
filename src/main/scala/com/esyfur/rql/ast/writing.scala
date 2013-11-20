package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql._
import com.esyfur.rql.core.MethodQuery

private[rql] class Insert[T <: Document](val table: Table, document: T, options: InsertOptions) extends MethodQuery(table, document) {

    protected val termType = TermType.INSERT
    val st = "insert"

    protected override val optArgs = options.toMap

}

private[rql] class Update[T <: Document](val table: Table, document: T, options: UpdateOptions) extends MethodQuery(table, document) {

    protected val termType = TermType.UPDATE
    val st = "update"

    protected override val optArgs = options.toMap

}

private[rql] class Replace(val table: Table, options: UpdateOptions) extends MethodQuery(table) {

    protected val termType = TermType.REPLACE
    val st = "replace"

    protected override val optArgs = options.toMap

}

private[rql] class Delete(val table: Table) extends MethodQuery(table) {

    protected val termType = TermType.DELETE
    val st = "delete"

}
