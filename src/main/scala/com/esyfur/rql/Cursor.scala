package com.esyfur.rql

import scala.collection.mutable.LinkedList

class Cursor(
    private val connection: Connection,
    private val query: Query,
    private val term: Term,
    chunk: String,
    var complete: Boolean
    ) {

    private val chunks: Seq[String] = LinkedList[String](chunk)

}
