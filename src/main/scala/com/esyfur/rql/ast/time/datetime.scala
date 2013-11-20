package com.esyfur.rql.ast.time

import com.rethinkdb.{Ql2 => p}
import p.Term.TermType

import com.esyfur.rql._
import com.esyfur.rql.core._

private[rql] class Now extends TopLevelQuery with TimeValue {

    protected val termType = TermType.NOW
    val st = "now"

}

private[rql] class Time(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Double, zone: String)
    extends MethodQuery(year, month, day, hour, minute, second, zone)
    with TimeValue {

    protected val termType = TermType.TIME
    val st = "time"

}

private[rql] class EpochTime(val value: Double) extends MethodQuery(value) with TimeValue {

    protected val termType = TermType.EPOCH_TIME
    val st = "epoch_time"

}

private[rql] class InTimezone(val value: TimeValue, val zone: String) extends MethodQuery(value, zone) with TimeValue {

    protected val termType = TermType.IN_TIMEZONE
    val st = "in_timezone"

}

private[rql] class Date(val value: TimeValue) extends MethodQuery(value) with TimeValue {

    protected val termType = TermType.DATE
    val st = "date"

}

private[rql] class ISO8601(val value: String) extends TopLevelQuery(value) with TimeValue {

    protected val termType = TermType.ISO8601
    val st = "iso8601"

}

private[rql] class Timezone(val value: TimeValue) extends MethodQuery(value) with StrValue {

    protected val termType = TermType.TIMEZONE
    val st = "timezone"

}

private[rql] class Year(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.YEAR
    val st = "year"

}

private[rql] class Month(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.MONTH
    val st = "month"

}

private[rql] class DayOfMonth(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.DAY
    val st = "day"

}

private[rql] class DayOfWeek(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.DAY_OF_WEEK
    val st = "day_of_week"

}

private[rql] class DayOfYear(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.DAY_OF_YEAR
    val st = "day_of_year"

}

private[rql] class TimeOfDay(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.TIME_OF_DAY
    val st = "time_of_day"

}

private[rql] class Hours(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.HOURS
    val st = "hours"

}

private[rql] class Minutes(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.MINUTES
    val st = "minutes"

}

private[rql] class Seconds(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.SECONDS
    val st = "seconds"

}

private[rql] class During(val value: TimeValue, val start: TimeValue, val end: TimeValue, val options: Option[SpanOptions])
    extends MethodQuery(value, start, end)
    with BoolValue {

    protected val termType = TermType.DURING
    val st = "during"

}

private[rql] class ToEpochTime(val value: TimeValue) extends MethodQuery(value) with NumValue {

    protected val termType = TermType.TO_EPOCH_TIME
    val st = "to_epoch_time"

}

private[rql] class ToISO8601(val value: TimeValue) extends MethodQuery(value) with StrValue {

    protected val termType = TermType.TO_ISO8601
    val st = "to_iso8601"

}
