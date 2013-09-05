package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql._
import org.joda.time.DateTime
import com.esyfur.rql.core.{TopLevelQuery, MethodQuery, TimeValue}

class Time(val value: DateTime) extends MethodQuery with TimeValue {

    protected val termType = p.Term.TermType.TIME
    val st = "time"

}

class Date extends MethodQuery with TimeValue {

    protected val termType = p.Term.TermType.DATE
    val st = "date"

}

class Now extends TopLevelQuery with TimeValue {

    protected val termType = p.Term.TermType.NOW
    val st = "now"

}

class EpochTime(val value: Long) extends TopLevelQuery(/*value*/) with TimeValue {

    protected val termType = p.Term.TermType.EPOCH_TIME
    val st = "epoch_time"

}

class ISO8601(val value: String) extends TopLevelQuery(value) with TimeValue {

    protected val termType = p.Term.TermType.ISO8601
    val st = "iso8601"

}

class Timezone extends MethodQuery {

    protected val termType = p.Term.TermType.TIMEZONE
    val st = "timezone"

}

class Year extends MethodQuery {

    protected val termType = p.Term.TermType.YEAR
    val st = "year"

}

class Month extends MethodQuery {

    protected val termType = p.Term.TermType.MONTH
    val st = "month"

}

class Day extends MethodQuery {

    protected val termType = p.Term.TermType.DAY
    val st = "day"

}

class DayOfWeek extends MethodQuery {

    protected val termType = p.Term.TermType.DAY_OF_WEEK
    val st = "day_of_week"

}

class DayOfYear extends MethodQuery {

    protected val termType = p.Term.TermType.DAY_OF_YEAR
    val st = "day_of_year"

}

class TimeOfDay extends MethodQuery {

    protected val termType = p.Term.TermType.TIME_OF_DAY
    val st = "time_of_day"

}

class Hours extends MethodQuery {

    protected val termType = p.Term.TermType.HOURS
    val st = "hours"

}

class Minutes extends MethodQuery {

    protected val termType = p.Term.TermType.MINUTES
    val st = "minutes"

}

class Seconds extends MethodQuery {

    protected val termType = p.Term.TermType.SECONDS
    val st = "seconds"

}

class During(start: TimeValue, end: TimeValue, options: Option[SpanOptions]) extends MethodQuery(start, end) {

    protected val termType = p.Term.TermType.DURING
    val st = "during"

}

class ToEpochTime(seconds: TimeValue) extends MethodQuery(seconds) {

    protected val termType = p.Term.TermType.TO_EPOCH_TIME
    val st = "to_epoch_time"

}

class ToISO8601 extends MethodQuery {

    protected val termType = p.Term.TermType.TO_ISO8601
    val st = "to_iso8601"

}

class InTimezone extends MethodQuery {

    protected val termType = p.Term.TermType.IN_TIMEZONE
    val st = "in_timezone"

}
