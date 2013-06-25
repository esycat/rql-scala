package com.esyfur.rql.ast

import com.esyfur.rql.Query
import com.rethinkdb.{Ql2 => p}

class MakeArray extends Query {
    protected val termType = p.Term.TermType.MAKE_ARRAY

}

class MakeObj extends Query {
    protected val termType = p.Term.TermType.MAKE_OBJ
}
