package com.esyfur.rql.ast.ops

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{Query, OpQuery, BiOpQuery}

class Any(a: Query, b: Query) extends BiOpQuery(a, b) {

    protected val termType = p.Term.TermType.ANY
    val st = "|"

}

class All(a: Query, b: Query) extends BiOpQuery(a, b) {

    protected val termType = p.Term.TermType.ALL
    val st = "&"

}

class Not(value: Query) extends OpQuery {

    protected val termType = p.Term.TermType.NOT
    val st = "~"

    /*
    def compose(args, optargs) = {
        if isinstance(self.args[ 0], Datum {
            args[ 0] = T('r.expr( ', args[ 0], ')')
            return T( ' ( ~ ', args[ 0], ')')
        }
    }
    */
}
