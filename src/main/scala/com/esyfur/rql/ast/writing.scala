package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{Table, MethodQuery, Document}

class Insert[T <: Document](val table: Table, document: T) extends MethodQuery(table, document) {

    protected val termType = p.Term.TermType.INSERT
    val st = "insert"

}

class Update[T <: Document](val table: Table, document: T) extends MethodQuery(table, document) {

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

class WriteOptions {

    var durability: Durability.Value = Durability.Soft
    var upsert: Boolean = true
    var returnVals: Boolean = true
    var noreply: Boolean = false

}

object Durability extends Enumeration {
    val Hard = Value("hard")
    val Soft = Value("soft")
}
