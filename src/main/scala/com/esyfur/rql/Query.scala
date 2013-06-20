package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}

abstract class Query extends Term {}

abstract class BiOpQuery extends Query {}

abstract class TopLevelQuery extends Query {}

abstract class MethodQuery extends Query {}
