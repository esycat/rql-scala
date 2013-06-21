package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{Query, BiOpQuery, TopLevelQuery, MethodQuery}

class Var extends Query {

    val termType = p.Term.TermType.VAR

    /*
    def compose(args, optargs) {
        return 'var_ '+ args[0]
    }
    */
}

class JavaScript extends TopLevelQuery {

    val termType = p.Term.TermType.JAVASCRIPT
    val st = "js"

}

class UserError extends TopLevelQuery {

    val termType = p.Term.TermType.ERROR
    val st = "error"

}

class Default extends Query {

    val termType = p.Term.TermType.DEFAULT
    val st = "default"

}

class ImplicitVar extends Query {

    val termType = p.Term.TermType.IMPLICIT_VAR

    /*
    def compose(args, optargs) {
        return 'r.row '
    }
    */
}

class Eq(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.EQ
    val st = "=="

}

class Ne(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.NE
    val st = "!="

}

class Lt(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.LT
    val st = "<"

}

class Le(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.LE
    val st = "<="

}

class Gt(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.GT
    val st = ">"

}

class Ge(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.GE
    val st = ">="

}

class Not(value: Query) extends Query {

    val termType = p.Term.TermType.NOT

    /*
    def compose(args, optargs) {
        if isinstance(self.args[ 0], Datum {
            args[ 0] = T('r.expr( ', args[ 0], ')')
            return T( ' ( ~ ', args[ 0], ')')
        }
    }
    */
}

class Add(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.ADD
    val st = "+"

}

class Sub(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.SUB
    val st = "-"

}

class Mul(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.MUL
    val st = "*"

}

class Div(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.DIV
    val st = "/"

}

class Mod(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.MOD
    val st = "%"

}

class Any(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.ANY
    val st = "|"

}

class All(a: Query, b: Query) extends BiOpQuery(a, b) {

    val termType = p.Term.TermType.ALL
    val st = "&"

}
