package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class Day4 : AdventOfCodePuzzle {

    override fun part1(input: List<String>): Any = with (Shift.parse(input)) {
         val guardWithMostMinutes = guardWithMostMinutes()
            guardWithMostMinutes * popularMinuteByGuard(guardWithMostMinutes)
    }

    override fun part2(input: List<String>): Any = with (
                Shift.parse(input)
                    .map { shift -> shift.minutesAsleep().map { minute -> shift.guard to minute } }.flatten()
                    .groupingBy { pair -> pair }.eachCount().maxBy { entry -> entry.value }
                    ?.key!!
        ) {first * second}

    private fun List<Shift>.guardWithMostMinutes() : Int = this.groupBy { shift -> shift.guard }.map { entry -> entry.key to entry.value.map { shift -> shift.minutesAsleep() }.flatten() }.maxBy { pair -> pair.second.size }?.first!!

    private fun List<Shift>.popularMinuteByGuard(guard : Int) = this.filter { shift -> shift.guard == guard }.map { shift -> shift.minutesAsleep() }.flatten().groupingBy { i -> i }.eachCount().maxBy { entry -> entry.value }?.key!!

    class Shift(val dateTime:LocalDateTime, val guard:Int, var logRecords:MutableList<LogRecord>) {

        fun minutesAsleep() : List<Int> {

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
            private fun from (logRecord:LogRecord) : Shift =
                Shift(logRecord.dateTime, LogRecord.SHIFT_START_REGEX.toRegex().find(logRecord.event)!!.groups[1]!!.value.toInt(), mutableListOf())

            fun parse(input: List<String>) : List<Shift> =
                mutableListOf<Shift>().apply {
                    input
                        .map { s -> LogRecord.from(s) }.sortedBy { logRecord -> logRecord.dateTime }
                        .forEach { logRecord ->
                            if (logRecord.isNewShift()) add(Shift.from(logRecord)) else last().logRecords.add(logRecord)
                        }
                }
        }
    }

    data class LogRecord(val dateTime:LocalDateTime, val event:String) {
        fun isNewShift(): Boolean = SHIFT_START_REGEX.toRegex().matches(event)
        companion object {
            const val SHIFT_START_REGEX = "Guard #([0-9]+) begins shift"
            fun from(s:String) : LogRecord = "^\\[([0-9-]{10}) ([0-9:]{5})\\] (.+)$".toRegex().find(s)!!.destructured.let { (date, time, event) -> LogRecord(LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time)), event) }
        }
    }
}

fun main() = Day4().solve()