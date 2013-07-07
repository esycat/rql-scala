package com.esyfur.rql

import scala.collection.mutable.LinkedList

import com.rethinkdb.{Ql2 => p}

class Cursor(
    private val connection: Connection,
    private val query: p.Query,
    /* private */ val chunk: Any,
    private val completed: Boolean = true
    ) {

    def next() = ???

    def hasNext: Boolean = false

    def each() = ???

    def toArray() = ???

    def close() = connection.end(query)

}
