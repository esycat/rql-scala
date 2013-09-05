package com.esyfur.rql.core

import com.esyfur.rql.ast._

trait Sequence extends Term with Joins { self: Sequence =>

    /* Transformations */

    def map(): Query = ???

    def withFields(fields: String*): Query = ???

    def concatMap(): Query = ???

    def orderBy(keys: String*): Query = new OrderBy(this, keys: _*)

    //def orderBy(keys: Ordering*) = new OrderBy(this, keys)

    def skip(number: Int): Query = new Skip(this, number)

    def limit(number: Int): Query = new Limit(this, number)

    def slice(startIndex: Int, endIndex: Int): Query = new Slice(this, startIndex, endIndex)

    def nth(index: Int): Query = ???

    def indexesOf(datum: Datum[Any]): Query = ???

    def indexesOf(predicate: Predicate): Query = ???

    def isEmpty(): Query = new IsEmpty(this)

    def union(query: Query): Query = new Union(this, query)

    def sample(number: Int): Query = new Sample(this, number)


    /* Aggregation */

    def reduce() = new Reduce(this)

    def count() = new Count(this)

    def distinct(): Query = new Distinct(this)

    def groupedMapReduce() = ???

    def groupBy() = ???

    def contains(values: Any*) = new Contains(this, values: _*)

    /* Document Manipulation */

    def pluck() = new Pluck(this)

    def without() = new Without(this)

}
