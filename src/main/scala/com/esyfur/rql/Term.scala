package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{MakeObj, MakeArray}

object Term {

    def needsWrapping(arg: Term): Boolean = arg match {
        //case Datum | MakeArray | MakeObj => true
        case _ => false
    }

}

abstract class Term {

    protected val termType: p.Term.TermType

    protected def getTermBuilder(): p.Term.Builder = p.Term.newBuilder().setType(termType)

    def build(): p.Term = getTermBuilder().build()

}
