package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}

object Term {

}

abstract class Term {

    protected val termType: p.Term.TermType

    protected def getTermBuilder(): p.Term.Builder = p.Term.newBuilder().setType(termType)

    def build(): p.Term = getTermBuilder().build()

}
