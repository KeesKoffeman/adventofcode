package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class Day4 : AdventOfCodePuzzle {

    override fun part1(input: List<String>): Any {

        val shifts = Shift.parse(input)
        val guardWithMostMinutes = shifts
                .groupBy { shift -> shift.guard }
                .map { entry -> Pair(entry.key, entry.value.map { shift -> shift.getMinutesAsleep() }.flatten()) }
                .maxBy { pair -> pair.second.size }
                ?.first

        val popularMinute = shifts
                .filter { shift -> shift.guard == guardWithMostMinutes }
                .map { shift -> shift.getMinutesAsleep() }
                .flatten()
                .groupingBy { i -> i }
                .eachCount()
                .maxBy { entry -> entry.value }
                ?.key

        return guardWithMostMinutes!! * popularMinute!!
    }

    override fun part2(input: List<String>): Any {

        val shifts = Shift.parse(input)
        val guardPopularMinutePair = shifts
                .map { shift -> shift.getMinutesAsleep().map { minute -> Pair(shift.guard, minute) } }
                .flatten()
                .groupingBy { pair -> pair }
                .eachCount()
                .maxBy { entry -> entry.value }
                ?.key

        return guardPopularMinutePair!!.first * guardPopularMinutePair.second
    }

    data class LogRecord(val dateTime:LocalDateTime, val event:String) {

        fun isNewShift(): Boolean {
            return "Guard #([0-9]+) begins shift".toRegex().matches(event)
        }

        companion object {
            fun from(s:String) : LogRecord {
                val find = "^\\[([0-9-]{10}) ([0-9:]{5})\\] (.+)$".toRegex().find(s)
                val (date, time, event) = find!!.destructured
                return LogRecord(LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time)), event)
            }
        }
    }

    class Shift(val dateTime:LocalDateTime, val guard:Int, var logRecords:MutableList<LogRecord>) {

        fun getMinutesAsleep() : List<Int> {

            if (logRecords.isEmpty()) {
                return emptyList()
            }

            var state = true
            var next = logRecords.sortedByDescending { logRecord -> logRecord.dateTime }.last()
            var remaining = logRecords.sortedByDescending { logRecord -> logRecord.dateTime }.dropLast(1)
            val minutesAsleep = mutableListOf<Int>()

            for (i in 0..59) {

                if (next.dateTime.minute == i) {
                    state = next.event.contains("wakes up")
                    if (remaining.isNotEmpty()) {
                        next = remaining.last()
                        remaining = remaining.sortedByDescending { logRecord -> logRecord.dateTime }.dropLast(1)
                    }
                }

                if (!state) {
                    minutesAsleep.add(i)
                }
            }
            return minutesAsleep
        }

        companion object {
            fun parse(input: List<String>) : List<Shift> {
                val shifts = mutableListOf<Shift>()
                input
                        .map { s -> LogRecord.from(s) }
                        .sortedBy { logRecord -> logRecord.dateTime }
                        .forEach { logRecord ->
                            if (logRecord.isNewShift()) {
                                val guard = "Guard #([0-9]+) begins shift".toRegex().find(logRecord.event)!!.groups[1]!!.value.toInt()
                                shifts.add(Shift(logRecord.dateTime, guard, mutableListOf()))
                            } else {
                                shifts.last().logRecords.add(logRecord)
                            }
                        }
                return shifts
            }
        }
    }


}

fun main() = Day4().solve()