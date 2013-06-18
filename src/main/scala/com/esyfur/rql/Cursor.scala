package com.esyfur.rql

import scala.collection.mutable.LinkedList

import com.rethinkdb.{Ql2 => p}

class Cursor(
    private val connection: Connection,
    private val query: p.Query,
    private val response: p.Response,
    chunk: String
    ) /*extends BufferedIterator*/ {

    private val chunks: Seq[String] = LinkedList[String](chunk)

    def next() {}

    def hasNext: Boolean = false

    def each() {}

    def toArray() {}


}
