package com.esyfur.rql.ast

import com.esyfur.rql.core._

abstract class JoinPredicate extends Function2[Var, Var, BoolDatum]

abstract class MappingFunction extends Function1[Var, Stream]

abstract class ReductionFunction extends Function1[Var, Stream]
