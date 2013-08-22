package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{Table, MethodQuery, Document, InsertOptions, UpdateOptions}

class Insert[T <: Document](val table: Table, document: T, options: InsertOptions) extends MethodQuery(table, document) {

    protected val termType = p.Term.TermType.INSERT
    val st = "insert"

    override val optArgs = options.toMap

}

class Update[T <: Document](val table: Table, document: T, options: UpdateOptions) extends MethodQuery(table, document) {

    protected val termType = p.Term.TermType.UPDATE
    val st = "update"

    override val optArgs = options.toMap

}

class Replace(val table: Table, options: UpdateOptions) extends MethodQuery(table) {

    protected val termType = p.Term.TermType.REPLACE
    val st = "replace"

    override val optArgs = options.toMap

}

class Delete(val table: Table) extends MethodQuery(table) {

    protected val termType = p.Term.TermType.DELETE
    val st = "delete"

}
