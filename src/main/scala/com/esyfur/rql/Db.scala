package com.esyfur.rql

import scala.collection.immutable.List

object Db {

}

class Db(val name: String) {

    def create(): Db = {
        this
    }

    def drop() = {

    }

    def createTable(name: String): Collection = {
        val collection = new Collection(name)
        collection.create()
    }

    def dropTable(name: String) = {
        val collection = new Collection(name)
        collection.drop()
    }

}
