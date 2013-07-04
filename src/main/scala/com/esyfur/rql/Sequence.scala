package com.esyfur.rql

import ast._

trait Sequence { self: Term =>

    def count() = new Count(this)

    def filter(predicate: Predicate) = new Filter(this, predicate)

    def withFields() = ???

    def concatMap() = ???

    def orderBy(keys: String*) = new OrderBy(this, keys: _*)

    //def orderBy(keys: Ordering*) = new OrderBy(this, keys)

    def limit(number: Int) = new Limit(this, number)

    def slice(startIndex: Int, endIndex: Int) = new Slice(this, startIndex, endIndex)

    def indexesOf() = ???

    def isEmpty() = ???

    def union(query: Query) = new Union(this, query)

    // Polymorphic object/sequence operations
    // def pluck(attrs: List[Query]) = new Pluck(attrs)

}
