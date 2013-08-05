package com.esyfur.rql

import scala.collection.mutable.LinkedList

import com.rethinkdb.{Ql2 => p}

class Cursor(
    private val connection: Connection,
    private val response: p.Response,
    /* private */ val chunk: Any
    ) extends Iterator[Any] with AutoCloseable {

    private var index = 0

    private def isPartial = (response.getType == p.Response.ResponseType.SUCCESS_PARTIAL)

    def hasNext: Boolean = {
        isPartial || index < response.getResponseCount
    }

    def next(): Any = ???

    def each() = ???

    def toArray() = ???

    def close(): Unit = connection.terminate(response.getToken)

}
