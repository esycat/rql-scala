package com.esyfur.rql

import ast._

trait Selection extends Term { self: Selection =>

    def between(lowerKey: String, upperKey: String, index: Option[String] = None): Query = new Between(this, lowerKey, upperKey, index)

    def filter(predicate: Predicate): Query = new Filter(this, predicate)

}
