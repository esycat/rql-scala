package com.esyfur.rql

import rethinkdb.{Ql2 => p}

object Db {

}

class Db(val name: String) extends Query {

    val termType = p.Term.TermType.DB

    def create(): Db = {
        this
    }

    def drop() = {

    }

    /*
    def tableList(): Query = {
    }

    def tableCreate(name: String): Query = {
    }

    def tableDrop(name: String): Query = {
    }
    */

}
