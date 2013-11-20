package com.esyfur.rql.ast.time

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql._
import com.esyfur.rql.core._

object TimeName {

    def apply(termType: TermType) = new TimeName(termType)

}

class TimeName(protected val termType: TermType) extends Query with NumValue
