package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql._
import com.esyfur.rql.core._

class Now extends TopLevelQuery with TimeValue {

    protected val termType = TermType.NOW
    val st = "now"

}

class Time(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Double, zone: String)
    extends MethodQuery(year, month, day, hour, minute, second, zone)
    with TimeValue {

    protected val termType = TermType.TIME
    val st = "time"

}

class EpochTime(val value: Double) extends MethodQuery(value) with TimeValue {

    protected val termType = TermType.EPOCH_TIME
    val st = "epoch_time"

}

class InTimezone(val value: TimeValue, val zone: String) extends MethodQuery(value, zone) with TimeValue {

    protected val termType = TermType.IN_TIMEZONE
    val st = "in_timezone"

}

class Date(val value: TimeValue) extends MethodQuery(value) with TimeValue {

    protected val termType = TermType.DATE
    val st = "date"

}

class ISO8601(val value: String) extends TopLevelQuery(value) with TimeValue {

    protected val termType = TermType.ISO8601
    val st = "iso8601"

}

class Timezone(val value: TimeValue) extends MethodQuery(value) with StrValue {

    protected val termType = TermType.TIMEZONE
    val st = "timezone"

}

class Year(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.YEAR
    val st = "year"

}

class Month(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.MONTH
    val st = "month"

}

class DayOfMonth(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.DAY
    val st = "day"

}

class DayOfWeek(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.DAY_OF_WEEK
    val st = "day_of_week"

}

class DayOfYear(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.DAY_OF_YEAR
    val st = "day_of_year"

}

class TimeOfDay(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.TIME_OF_DAY
    val st = "time_of_day"

}

class Hours(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.HOURS
    val st = "hours"

}

class Minutes(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.MINUTES
    val st = "minutes"

}

class Seconds(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.SECONDS
    val st = "seconds"

}

class During(val value: TimeValue, val start: TimeValue, val end: TimeValue, val options: Option[SpanOptions])
    extends MethodQuery(value, start, end)
    with BoolValue {

    protected val termType = TermType.DURING
    val st = "during"

}

class ToEpochTime(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.TO_EPOCH_TIME
    val st = "to_epoch_time"

}

class ToISO8601(val value: TimeValue) extends MethodQuery(value) with StrValue {

    protected val termType = TermType.TO_ISO8601
    val st = "to_iso8601"

}
