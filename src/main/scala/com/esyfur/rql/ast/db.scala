package com.esyfur.rql.ast

import com.esyfur.rql.{Query, TopLevelQuery, MethodQuery}
import rethinkdb.{Ql2 => p}

class DbList extends TopLevelQuery {
    val termType = p.Term.TermType.DB_LIST
    state = "db_list"
}

class DbCreate extends TopLevelQuery {
    val termType = p.Term.TermType.DB_CREATE
    state = "db_create"
}

class DbDrop extends TopLevelQuery {
    val termType = p.Term.TermType.DB_DROP
    state = "db_drop"
}
