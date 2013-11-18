package com.esyfur.rql.test

import org.joda.time.{LocalDateTime, DateTimeZone, DateTime}
import org.scalautils._

import com.esyfur.{rql => r}
import com.esyfur.rql.{Document, Cursor}
import com.esyfur.rql.util._

class DateTimeSpec extends UnitSpec with ConnectionAndDatabase with Tolerance {

    private val tz = DateTimeZone.forOffsetHours(10)

    private def assertTime(ts: DateTime, tolerance: Option[Double] = None)(cursor: Cursor): Unit = {
        assertNotEmpty(cursor)

        val document = cursor.next().asInstanceOf[Document]
        val timeZone = document("timezone").asInstanceOf[String]
        val epochTime = document("epoch_time").asInstanceOf[Double]

        timeZone should be (getTzOffset(ts.getZone))

        tolerance match {
            case Some(tol) => epochTime should equal (toEpochTime(ts) +- tol)
            case None      => epochTime should equal (toEpochTime(ts))
        }
    }

    describe("RQL") {
        describe("should be able to create a time object") {
            it("that represents current time in UTC") {
                val ts = currentTime // current date-time with up to a minute error
                val cursor = r.now().run
                assertTime(ts, Some(60L))(cursor)
            }

            it("for a specific date in UTC") {
                val ts = currentTime.withTimeAtStartOfDay // current date at midnight
                val cursor = r.time(ts.getYear, ts.getMonthOfYear, ts.getDayOfMonth).run
                assertTime(ts)(cursor)
            }

            it("for a specific date in a given timezone") {
                // current date at midnight converted to the given timezone
                val ts = currentTime.withTimeAtStartOfDay.withZoneRetainFields(tz)
                val cursor = r.time(
                    ts.getYear, ts.getMonthOfYear, ts.getDayOfMonth,
                    getTzOffset(ts.getZone)
                ).run
                assertTime(ts)(cursor)
            }

            it("for a specific date and time in a given timezone") {
                // current date-time converted to the given timezone
                val ts = currentTime.withZoneRetainFields(tz)
                val cursor = r.time(
                    ts.getYear, ts.getMonthOfYear, ts.getDayOfMonth,
                    ts.getHourOfDay, ts.getMinuteOfHour, ts.getSecondOfMinute + ts.getMillisOfSecond / 1000.0,
                    getTzOffset(ts.getZone)
                ).run
                assertTime(ts)(cursor)
            }

            it("based on seconds since epoch") {
                val ts = currentTime // current date-time
                val cursor = r.epochTime(toEpochTime(ts)).run
                assertTime(ts)(cursor)
            }

            it("based on an ISO 8601 string") {
                // current date-time converted to the given timezone
                val ts = currentTime.withZoneRetainFields(tz)
                val cursor = r.iso8601(ts.toString).run
                assertTime(ts)(cursor)
            }
        }

        it("should have defined weekdays") {

        }
    }

    describe("A time object") {
        describe("should be able to return") {
            describe("a new time object") {
                it("with a different timezone") {
                    val ts = currentTime
                    val expected = ts.withZone(tz)
                    val cursor = r.iso8601(ts.toString).inTimezone(getTzOffset(tz)).run
                    assertTime(expected)(cursor)

                }

                it("based only on its date") {
                    val ts = currentTime
                    val expected = ts.withTimeAtStartOfDay()
                    val cursor = r.iso8601(ts.toString).date().run
                    assertTime(expected)(cursor)
                }
            }

            it("number of seconds elapsed since the beginning of the day") {
                val ts = currentTime
                val expected = ts.getMillisOfDay / 1000.0
                val cursor = r.expr(ts).timeOfDay().run
                assertCursor(expected, cursor)
            }
        }
    }

}
