package com.esyfur.rql.ast

import com.esyfur.rql.{Term, TopLevelQuery, MethodQuery}
import com.rethinkdb.{Ql2 => p}


class MakeArray extends Term {
    val termType = p.Term.TermType.MAKE_ARRAY

}

class MakeObj extends Term {
    val termType = p.Term.TermType.MAKE_OBJ

}
