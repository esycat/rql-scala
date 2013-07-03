package com.esyfur.rql

import ast._

trait Sequence { self: Term =>

    def count() = new Count(this)

    def filter(predicate: Predicate) = new Filter(predicate)

    def withFields() = ???

    def concatMap() = ???

    def orderBy() = ???

    def limit() = ???

    def slice() = ???

    def indexesOf() = ???

    def isEmpty() = ???

    def union(query: Query) = new Union(query)

    // Polymorphic object/sequence operations
    // def pluck(attrs: List[Query]) = new Pluck(attrs)

}
