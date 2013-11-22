package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.core._

private[rql] class OrderBy(self: Sequence, keys: String*) extends MethodQuery(self /*, keys: _* */) with Stream {

    protected val termType = TermType.ORDERBY

}

abstract sealed class Ordering(val attr: String) extends ValueQuery(attr)

private[rql] class Asc(attr: String) extends Ordering(attr) {

    protected val termType = TermType.ASC
    val st = "asc"

}

private[rql] class Desc(attr: String) extends Ordering(attr) {

    protected val termType = TermType.DESC
    val st = "desc"

}
