package com.esyfur.rql.core

import com.esyfur.rql.ast._

private[rql] trait Sequence extends Query with Joins {

    def default() = ???

    /* Transformations */

    def map(): Stream = new Map(this)

    def withFields(fields: String*): Stream = new WithFields(this)

    def concatMap(): Stream = new ConcatMap(this)

    def orderBy(keys: String*): Stream = new OrderBy(this, keys: _*)

    //def orderBy(keys: Ordering*) = new OrderBy(this, keys)

    def skip(number: Int): Stream = new Skip(this, number)

    def limit(number: Int): Stream = new Limit(this, number)

    def slice(startIndex: Int, endIndex: Int): Stream = new Slice(this, startIndex, endIndex)

    def nth(index: Int): Nth = new Nth(this, index)

    def indexesOf(datum: Datum[Any]): Query = ???

    def indexesOf(predicate: Predicate): Query = ???

    def isEmpty(): IsEmpty = new IsEmpty(this)

    def union(other: Sequence): Query = new Union(this, other)

    def sample(number: Int): Selection = new Sample(this, number)


    /* Aggregation */

    def reduce(): Reduce = new Reduce(this)

    def count(): Count = new Count(this)

    def distinct(): Distinct = new Distinct(this)

    def groupedMapReduce() = ???

    def groupBy(reductor: Aggregator, attr: String, attrs: String*): Query = new GroupBy(this, reductor, attr +: attrs)

    def contains(values: Any*): Contains = new Contains(this, values: _*)

    /* Document Manipulation */

    def pluck(): Pluck = new Pluck(this)

    def without(): Without = new Without(this)

}
