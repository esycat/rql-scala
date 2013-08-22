package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{MethodQuery, TopLevelQuery}

class Time extends MethodQuery {

    protected val termType = p.Term.TermType.TIME
    val st = "time"

    def inTimezone(): Time = ???

    def timezone(): String = ???

    def during(): Boolean = ???

    def date(): Time = ???

    def timeOfDay(): Short = ???

    def year(): Short = ???

    def month(): Short = ???

    def day(): Short = ???

    def dayOfWeek(): Short = ???

    def dayOfYear(): Short = ???

    def hours(): Short = ???

    def minutes(): Short = ???

    def seconds(): Short = ???

    def toISO8601(): Integer = ???

    def toEpochTime(): Integer = ???

}

class Date extends MethodQuery {
    protected val termType = p.Term.TermType.DATE
    val st = "date"
}

class Now extends TopLevelQuery {
    protected val termType = p.Term.TermType.NOW
    val st = "now"
}

class Timezone extends MethodQuery {
    protected val termType = p.Term.TermType.TIMEZONE
    val st = "timezone"
}

class ToISO8601 extends MethodQuery {
    protected val termType = p.Term.TermType.TO_ISO8601
    val st = "to_iso8601"
}

class During extends MethodQuery {
    protected val termType = p.Term.TermType.DURING
    val st = "during"
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

class ISO8601 extends TopLevelQuery {
    protected val termType = p.Term.TermType.ISO8601
    val st = "iso8601"
}

class EpochTime extends TopLevelQuery {
    protected val termType = p.Term.TermType.EPOCH_TIME
    val st = "epoch_time"
}

class InTimezone extends MethodQuery {
    protected val termType = p.Term.TermType.IN_TIMEZONE
    val st = "in_timezone"
}

class ToEpochTime extends MethodQuery {
    protected val termType = p.Term.TermType.TO_EPOCH_TIME
    val st = "to_epoch_time"
}
