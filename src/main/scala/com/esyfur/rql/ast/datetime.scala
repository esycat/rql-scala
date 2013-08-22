package com.esyfur.rql.ast

import com.esyfur.rql.TopLevelQuery

class Time extends TopLevelQuery {

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
