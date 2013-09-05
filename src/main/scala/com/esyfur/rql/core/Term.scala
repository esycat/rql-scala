package com.esyfur.rql.core

import com.rethinkdb.{Ql2 => p}

private[rql] abstract class Term {

    protected val termType: p.Term.TermType

    protected def getTermBuilder(): p.Term.Builder = p.Term.newBuilder().setType(termType)

    def build(): p.Term = getTermBuilder().build()

}
