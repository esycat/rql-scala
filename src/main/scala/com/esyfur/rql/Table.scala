package com.esyfur.rql

import rethinkdb.{Ql2 => p}

object Table {

}

class Table(val name: String) extends Query {

    val termType = p.Term.TermType.TABLE

    def create(): Table = {
        this
    }

    def drop() = {

    }

    def insert() = {

    }

    def update() = {

    }

    def replace() = {

    }

    def delete() = {

    }

    def indexList() = {

    }

    def indexCreate() = {

    }

    def indexDrop() = {

    }

    /*
    def count(): Query = {
    }
    */

}
