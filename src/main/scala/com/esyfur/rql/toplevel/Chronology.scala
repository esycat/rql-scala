package com.esyfur.rql.toplevel

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql.ast.time._

private[rql] trait Chronology {

    def now(): Now = new Now()

    def time(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Double, zone: String): Time = new Time(
        year, month, day, hour, minute, second, zone
    )

    def time(year: Int, month: Int, day: Int, zone: String = "Z"): Time = time(
        year, month, day, 0, 0, 0, zone
    )

    def epochTime(seconds: Double): EpochTime = new EpochTime(seconds)

    def epochTime(millis: Long): EpochTime = epochTime(millis / 1000.0)

    def iso8601(dateString: String, defaultTimezone: Option[String] = None): ISO8601 = new ISO8601(dateString)


    // Week Day Names
    final lazy val monday    : TimeName = TimeName(TermType.MONDAY)
    final lazy val tuesday   : TimeName = TimeName(TermType.TUESDAY)
    final lazy val wednesday : TimeName = TimeName(TermType.WEDNESDAY)
    final lazy val thursday  : TimeName = TimeName(TermType.THURSDAY)
    final lazy val friday    : TimeName = TimeName(TermType.FRIDAY)
    final lazy val saturday  : TimeName = TimeName(TermType.SATURDAY)
    final lazy val sunday    : TimeName = TimeName(TermType.SUNDAY)

    final lazy val weekdays: Seq[TimeName] = Seq(
        monday, tuesday, wednesday, thursday, friday, saturday, sunday
    )

    def weekday(idx: Int): TimeName = weekdays(idx - 1)

    // Month Names
    final lazy val january   : TimeName = TimeName(TermType.JANUARY)
    final lazy val february  : TimeName = TimeName(TermType.FEBRUARY)
    final lazy val march     : TimeName = TimeName(TermType.MARCH)
    final lazy val april     : TimeName = TimeName(TermType.APRIL)
    final lazy val may       : TimeName = TimeName(TermType.MAY)
    final lazy val june      : TimeName = TimeName(TermType.JUNE)
    final lazy val july      : TimeName = TimeName(TermType.JULY)
    final lazy val august    : TimeName = TimeName(TermType.AUGUST)
    final lazy val september : TimeName = TimeName(TermType.SEPTEMBER)
    final lazy val october   : TimeName = TimeName(TermType.OCTOBER)
    final lazy val november  : TimeName = TimeName(TermType.NOVEMBER)
    final lazy val december  : TimeName = TimeName(TermType.DECEMBER)

    final lazy val months: Seq[TimeName] = Seq(
        january, february, march, april, may, june, july, august, september, october, november, december
    )

    def month(idx: Int): TimeName = months(idx - 1)

}
