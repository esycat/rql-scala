package com.esyfur.rql.ast

import com.esyfur.rql.{Query, TopLevelQuery, MethodQuery}
import rethinkdb.{Ql2 => p}


class MakeArray extends Query {
    val termType = p.Term.TermType.MAKE_ARRAY

}

class MakeObj extends Query {
    val termType = p.Term.TermType.MAKE_OBJ

}
