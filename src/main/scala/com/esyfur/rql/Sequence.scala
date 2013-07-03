package com.esyfur.rql

import ast._

trait Sequence {

    def count() = new Count()

    def filter(predicate: Predicate) = new Filter(predicate)

    def withFields() = ???

    def concatMap() = ???

    def orderBy() = ???

    def limit() = ???

    def slice() = ???

    def indexesOf() = ???

    def isEmpty() = ???

    def union(query: Query) = new Union(query)

}
