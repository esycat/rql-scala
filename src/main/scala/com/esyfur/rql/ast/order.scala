package com.esyfur.rql.ast

import com.esyfur.rql.{Term, TopLevelQuery, MethodQuery}
import com.rethinkdb.{Ql2 => p}

abstract class Order extends Term {

}

/*
class Asc extends Order {
    val termType = p.Term.TermType._
}

class Desc extends Order {
    val termType = p.Term.TermType._
}
*/
