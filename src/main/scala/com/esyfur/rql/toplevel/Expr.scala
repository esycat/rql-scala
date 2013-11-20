package com.esyfur.rql.toplevel

import java.util.Date
import org.joda.time.DateTime

import com.esyfur.rql.core._

private[rql] trait Expr { self: Chronology =>

    def expr(value: Boolean): BoolValue = Datum(value)

    def expr(value: Int): NumValue = Datum(value)

    def expr(value: Long): NumValue = Datum(value)

    def expr(value: Float): NumValue = Datum(value)

    def expr(value: Double): NumValue = Datum(value)

    def expr(value: String): StrValue = Datum(value)

    def expr(value: DateTime): TimeValue = iso8601(value.toString)

    def expr(value: Date): TimeValue = expr(new DateTime(value))

    def expr(value: Option[Any]): Datum[Any] = Datum(value)

    def expr(value: Any): Term = value match {
        case v: Term => v
        case _ => Datum(value)
    }

}
