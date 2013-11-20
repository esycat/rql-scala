package com.esyfur.rql.core

import scala.util.matching.Regex

import com.esyfur.rql.ast._
import com.esyfur.rql.ast.ops._
import com.esyfur.rql.ast.time._
import com.esyfur.rql.SpanOptions

private[rql] trait Value extends Term { self: Value =>

    // Comparison operators
    def eq(that: Value) = new Eq(this, that)
    def ne(that: Value) = new Ne(this, that)
    def lt(that: Value) = new Lt(this, that)
    def le(that: Value) = new Le(this, that)
    def gt(that: Value) = new Gt(this, that)
    def ge(that: Value) = new Ge(this, that)

    def ==(that: Value) = eq(that)
    def !=(that: Value) = ne(that)
    def < (that: Value) = lt(that)
    def <=(that: Value) = le(that)
    def > (that: Value) = gt(that)
    def >=(that: Value) = ge(that)

}

private[rql] trait BoolValue extends Term { self: BoolValue =>

    // Logical operators
    def not()                = new Not(this)
    def and(that: BoolValue) = new All(this, that)
    def or(that: BoolValue)  = new Any(this, that)

    def !                  = not()
    def &(that: BoolValue) = and(that)
    def |(that: BoolValue) = or(that)

}

private[rql] trait NumValue extends Term { self: NumValue =>

    // Numeric operators
    def add(that: NumValue) = new Add(this, that)
    def add(that: Double): Add = add(Datum(that))

    def sub(that: NumValue) = new Sub(this, that)
    def sub(that: Double): Sub = sub(Datum(that))

    def mul(that: NumValue) = new Mul(this, that)
    def mul(that: Double): Mul = mul(Datum(that))

    def div(that: NumValue) = new Div(this, that)
    def div(that: Double): Div = div(Datum(that))

    def mod(that: NumValue) = new Mod(this, that)
    def mod(that: Double): Mod = mod(Datum(that))

    def +(that: NumValue) = add(that)
    def -(that: NumValue) = sub(that)
    def *(that: NumValue) = mul(that)
    def /(that: NumValue) = div(that)
    def %(that: NumValue) = mod(that)

}

private[rql] trait StrValue extends Term { self: StrValue =>

    def matchWith(regexp: String) = new Match(this, regexp)
    def matchWith(regexp: Regex)  = new Match(this, regexp.toString())

}

private[rql] trait TimeValue extends Term { self: TimeValue =>

    def toISO8601() = new ToISO8601(this)

    def toEpochTime() = new ToEpochTime(this)

    def date() = new Date(this)

    def year() = new Year(this)

    def month() = new Month(this)

    def day() = dayOfMonth()

    def dayOfMonth() = new DayOfMonth(this)

    def dayOfWeek() = new DayOfWeek(this)

    def dayOfYear() = new DayOfYear(this)

    def timeOfDay() = new TimeOfDay(this)

    def hours() = new Hours(this)

    def minutes() = new Minutes(this)

    def seconds() = new Seconds(this)

    def timezone() = new Timezone(this)

    def inTimezone(timezone: String) = new InTimezone(this, timezone)

    def during(start: TimeValue, end: TimeValue, options: Option[SpanOptions] = None) = new During(this, start, end, options)

}
