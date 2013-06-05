package com.esyfur.rql.ast

import com.esyfur.rql.{Query, TopLevelQuery, MethodQuery}
import rethinkdb.{Ql2 => p}

abstract class Order extends Query {

}

/*
class Asc extends Order {
    val termType = p.Term.TermType._
}

class Desc extends Order {
    val termType = p.Term.TermType._
}
*/
