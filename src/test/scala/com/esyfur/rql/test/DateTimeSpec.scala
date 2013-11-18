package com.esyfur.rql.test

import org.joda.time.{LocalDateTime, DateTimeZone, DateTime}
import org.scalautils._

import com.esyfur.{rql => r}
import com.esyfur.rql.{Document, Cursor}
import com.esyfur.rql.util._

class DateTimeSpec extends UnitSpec with ConnectionAndDatabase with Tolerance {

    private val timeZone = DateTimeZone.forOffsetHours(10)

    private def seconds(dateTime: DateTime): Long = dateTime.getMillis / 1000L

    private def assertTime(dateTime: DateTime, tolerance: Option[Long] = None)(cursor: Cursor): Unit = {
        assertNotEmpty(cursor)

        val document = cursor.next().asInstanceOf[Document]
        val timeZone = document("timezone")
        val epochTime = document("epoch_time").asInstanceOf[Double].toLong

        timeZone should be (getTzOffset(dateTime.getZone))

        tolerance match {
            case Some(tol) => epochTime should equal (seconds(dateTime) +- tol)
            case None      => epochTime should equal (seconds(dateTime))
        }
    }

    describe("RQL") {
        describe("should be able to create a time object") {
            it("that represents current time in UTC") {
                val dateTime = currentTime // current date-time with up to a minute error
                val cursor = r.now().run
                assertTime(dateTime, Some(60L))(cursor)
            }

            it("for a specific date in UTC") {
                val dateTime = currentTime.withTimeAtStartOfDay // current date at midnight
                val cursor = r.time(dateTime.getYear, dateTime.getMonthOfYear, dateTime.getDayOfMonth).run
                assertTime(dateTime)(cursor)
            }

            it("for a specific date in a given timezone") {
                // current date at midnight converted to the given timezone
                val dateTime = currentTime.withTimeAtStartOfDay.withZoneRetainFields(timeZone)
                val cursor = r.time(
                    dateTime.getYear, dateTime.getMonthOfYear, dateTime.getDayOfMonth,
                    getTzOffset(dateTime.getZone)
                ).run
                assertTime(dateTime)(cursor)
            }

            it("for a specific date and time in a given timezone") {
                // current date-time converted to the given timezone
                val dateTime = currentTime.withZoneRetainFields(timeZone)
                val cursor = r.time(
                    dateTime.getYear, dateTime.getMonthOfYear, dateTime.getDayOfMonth,
                    dateTime.getHourOfDay, dateTime.getMinuteOfHour, dateTime.getSecondOfMinute,
                    getTzOffset(dateTime.getZone)
                ).run
                assertTime(dateTime)(cursor)
            }

            it("based on seconds since epoch") {
                val dateTime = currentTime // current date-time
                val cursor = r.epochTime(seconds(dateTime)).run
                assertTime(dateTime)(cursor)
            }

            it("based on an ISO 8601 string") {
                // current date-time converted to the given timezone
                val dateTime = currentTime.withZoneRetainFields(timeZone)
                val cursor = r.iso8601(dateTime.toString).run
                assertTime(dateTime)(cursor)
            }
        }

        it("should have defined weekdays") {

        }
    }

    describe("A time object") {
        describe("should be able to return") {
            describe("a new time object") {
                it("with a different timezone") {
                    val dateTime = currentTime
                    val expected = dateTime.withZone(timeZone)
                    val cursor = r.iso8601(dateTime.toString).inTimezone(getTzOffset(timeZone)).run
                    assertTime(expected)(cursor)

                }

                it("based only on its date") {
                    val dateTime = currentTime
                    val expected = dateTime.withTimeAtStartOfDay()
                    val cursor = r.iso8601(dateTime.toString).date().run
                    assertTime(expected)(cursor)
                }
            }

            it("number of seconds elapsed since the beginning of the day") {
                val dateTime = currentTime
                val expected = dateTime.getSecondOfDay +- 0.5
                val cursor = r.expr(dateTime).timeOfDay().run
                assertCursor(expected, cursor)
            }
        }
    }

}
