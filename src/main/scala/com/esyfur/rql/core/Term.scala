package com.esyfur.rql.core

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

private[rql] abstract class Term {

    protected val termType: p.Term.TermType

    protected def getTermBuilder(): p.Term.Builder = p.Term.newBuilder().setType(termType)

    protected[rql] def build(): p.Term = getTermBuilder().build()

}
