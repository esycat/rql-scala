package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.Query

class MakeArray extends Query {
    protected val termType = p.Term.TermType.MAKE_ARRAY

}

class MakeObj extends Query {
    protected val termType = p.Term.TermType.MAKE_OBJ
}
