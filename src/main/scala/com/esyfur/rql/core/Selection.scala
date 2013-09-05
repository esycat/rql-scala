package com.esyfur.rql.core

import com.esyfur.rql.ast.{Filter, Predicate, Between}

trait Selection extends Term { self: Selection =>

    def between(lowerKey: String, upperKey: String, index: Option[String] = None): Query = new Between(this, lowerKey, upperKey, index)

    def filter(predicate: Predicate): Query = new Filter(this, predicate)

}
