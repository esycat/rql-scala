package com.esyfur.rql

import java.util.TimeZone
import org.joda.time.{DateTime, DateTimeZone}

package object util {

    private[rql] def printCursor(cursor: Cursor): Unit = {
        if (cursor.hasNext) println(cursor.next())
        else println("Cursor empty")
    }

    private[rql] def getTzOffset(timeZone: TimeZone): String = {
        val tpl = "%s%02d:%02d"
        val offset = timeZone.getRawOffset()
        val sign = if (offset < 0) "-" else "+"
        tpl.format(sign, Math.abs(offset) / 3600000, Math.abs(offset) / 60000 % 60)
    }

    private[rql] def getTzOffset(timeZone: DateTimeZone): String = getTzOffset(timeZone.toTimeZone)

    private[rql] def toEpochTime(dateTime: DateTime): Double = dateTime.getMillis / 1000.0

    private[rql] def currentTime: DateTime = new DateTime(DateTimeZone.UTC)

}
