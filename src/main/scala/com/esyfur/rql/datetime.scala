package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}

import com.esyfur.rql.ast.TimeName

object datetime {

    lazy val monday    : TimeName = TimeName(p.Term.TermType.MONDAY)
    lazy val tuesday   : TimeName = TimeName(p.Term.TermType.TUESDAY)
    lazy val wednesday : TimeName = TimeName(p.Term.TermType.WEDNESDAY)
    lazy val thursday  : TimeName = TimeName(p.Term.TermType.THURSDAY)
    lazy val friday    : TimeName = TimeName(p.Term.TermType.FRIDAY)
    lazy val saturday  : TimeName = TimeName(p.Term.TermType.SATURDAY)
    lazy val sunday    : TimeName = TimeName(p.Term.TermType.SUNDAY)

    lazy val weekdays: Seq[TimeName] = Seq(
        monday, tuesday, wednesday, thursday, friday, saturday, sunday
    )

    def weekday(idx: Int): TimeName = weekdays(idx - 1)

    lazy val january   : TimeName = TimeName(p.Term.TermType.JANUARY)
    lazy val february  : TimeName = TimeName(p.Term.TermType.FEBRUARY)
    lazy val march     : TimeName = TimeName(p.Term.TermType.MARCH)
    lazy val april     : TimeName = TimeName(p.Term.TermType.APRIL)
    lazy val may       : TimeName = TimeName(p.Term.TermType.MAY)
    lazy val june      : TimeName = TimeName(p.Term.TermType.JUNE)
    lazy val july      : TimeName = TimeName(p.Term.TermType.JULY)
    lazy val august    : TimeName = TimeName(p.Term.TermType.AUGUST)
    lazy val september : TimeName = TimeName(p.Term.TermType.SEPTEMBER)
    lazy val october   : TimeName = TimeName(p.Term.TermType.OCTOBER)
    lazy val november  : TimeName = TimeName(p.Term.TermType.NOVEMBER)
    lazy val december  : TimeName = TimeName(p.Term.TermType.DECEMBER)

    lazy val months: Seq[TimeName] = Seq(
        january, february, march, april, may, june, july, august, september, october, november, december
    )

    def month(idx: Int): TimeName = months(idx - 1)

}
