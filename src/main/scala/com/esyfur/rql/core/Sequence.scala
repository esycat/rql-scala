package com.esyfur.rql.core

import com.esyfur.rql.ast._

private[rql] trait Sequence extends Query with Joins {

    def default(value: Any): Default = new Default(this, value)

    /* Transformations */

    def map(): Stream = new Map(this)

    def withFields(fields: String*): Stream = new WithFields(this)

    def concatMap(): Stream = new ConcatMap(this)

    def orderBy(key: String, keys: String*): Stream = new OrderBy(this, key +: keys)

    // def orderBy(key: Ordering, keys: Ordering*) = new OrderBy(this, key +: keys)

    def skip(n: Int): Stream = new Skip(this, n)

    def limit(n: Int): Stream = new Limit(this, n)

    def slice(startIndex: Int, endIndex: Int): Stream = new Slice(this, startIndex, endIndex)

    def nth(index: Int): Nth = new Nth(this, index)

    def indexesOf(datum: Datum[Any]): Query = ???

    def indexesOf(predicate: Predicate): Query = ???

    def isEmpty(): IsEmpty = new IsEmpty(this)

    def union(other: Sequence): Query = new Union(this, other)

    def sample(n: Int): Selection = new Sample(this, n)


    /* Aggregation */

    def reduce(): Reduce = new Reduce(this)

    def count(): Count = new Count(this)

    def distinct(): Distinct = new Distinct(this)

    def groupedMapReduce(grouping: Predicate, mapping: Predicate, reduction: Predicate, base: Predicate): GroupedMapReduce
        = new GroupedMapReduce(this, grouping, mapping, reduction, base)

    def groupBy(reduction: Aggregator, attr: String, attrs: String*): Query = new GroupBy(this, reduction, attr +: attrs)

    def contains(values: Any*): Contains = new Contains(this, values: _*)

    /* Document Manipulation */

    def pluck(): Pluck = new Pluck(this)

    def without(): Without = new Without(this)

}
