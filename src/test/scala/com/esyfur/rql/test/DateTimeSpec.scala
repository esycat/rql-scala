package com.esyfur.rql.test

import org.joda.time.{LocalDateTime, DateTimeZone, DateTime}
import org.scalautils._

import com.esyfur.{rql => r}
import com.esyfur.rql.{Document, Cursor}
import com.esyfur.rql.util._

class DateTimeSpec extends UnitSpec with ConnectionAndDatabase with Tolerance {

    private val timeZone = DateTimeZone.forOffsetHours(10)

    private def seconds(dateTime: DateTime): Long = dateTime.getMillis / 1000L
    private def epochTime(document: Document): Long = document("epoch_time").asInstanceOf[Double].toLong

    private def assertTime(dateTime: DateTime)(cursor: Cursor): Unit = {
        assertNotEmpty(cursor)

        val document = cursor.next().asInstanceOf[Document]

        epochTime(document) should equal (seconds(dateTime))
        document("timezone") should be (getTzOffset(dateTime.getZone))
    }

    describe("RQL") {
        it("should be able to create a time object that represents current time in UTC") {
            val dateTime = currentTime
            val cursor = r.now().run
            assertNotEmpty(cursor)

            val document = cursor.next().asInstanceOf[Document]
            document("timezone") should be (getTzOffset(dateTime.getZone))
            epochTime(document) should equal (seconds(dateTime) +- 60)
        }

        it("should be able to create a time object for a specific date in UTC") {
            val dateTime = currentTime.withTimeAtStartOfDay
            val cursor = r.time(dateTime.getYear, dateTime.getMonthOfYear, dateTime.getDayOfMonth).run
            assertTime(dateTime)(cursor)
        }

        it("should be able to create a time object for a specific date in a given timezone") {
            val dateTime = currentTime.withTimeAtStartOfDay.withZoneRetainFields(timeZone)
            val cursor = r.time(
                dateTime.getYear, dateTime.getMonthOfYear, dateTime.getDayOfMonth,
                getTzOffset(dateTime.getZone)
            ).run
            assertTime(dateTime)(cursor)
        }

        it("should be able to create a time object for a specific date and time in a given timezone") {
            val dateTime = currentTime.withZoneRetainFields(timeZone)
            val cursor = r.time(
                dateTime.getYear, dateTime.getMonthOfYear, dateTime.getDayOfMonth,
                dateTime.getHourOfDay, dateTime.getMinuteOfHour, dateTime.getSecondOfMinute,
                getTzOffset(dateTime.getZone)
            ).run
            assertTime(dateTime)(cursor)
        }

        it("should be able to create a time object based on seconds since epoch") {
            val dateTime = currentTime
            val cursor = r.epochTime(seconds(dateTime)).run
            assertTime(dateTime)(cursor)
        }

        it("should be able to create a time object based on an ISO 8601 string") {
            val dateTime = currentTime.withZoneRetainFields(timeZone)
            val cursor = r.iso8601(dateTime.toString).run
            assertTime(dateTime)(cursor)
        }

    }

    describe("A time object") {

    }

}
