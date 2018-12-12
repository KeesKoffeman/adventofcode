package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle
import java.util.*

class Day12 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = Pots(input).grow(20)

    override fun part2(input: List<String>) = Pots(input).part2(50000000000)
}

class Pots(input: List<String>) {

    private val patterns : Map<String, Char> = input.subList(2, input.size).map { it.substring(0, 5) to it[it.length - 1] }.toMap()
    private var state: LinkedList<Char> = LinkedList(input[0].replace("initial state: ", "").toList())
    private var age = 0
    private var index = 0

    fun part2(times: Long): Long {

        val g100 = grow(100)
        val g200 = grow(100)

        val d = g200 - g100

        return (times - 100) / 100L * d + g100
    }

    fun grow(times: Int): Int {
        repeat(times) {

            printCurrentState()

            while (state.indexOf('#') < 5) { state.push('.'); index++ }
            while (state.takeLast(5) != listOf('.', '.', '.', '.', '.')) { state.add('.') }

            state = LinkedList(state.windowed(5,1).map { w-> patterns[String(w.toCharArray())]?:'.' }.toList())
            index -= 2
            age++
        }

        printCurrentState()
        return points()
    }

    private fun printCurrentState() = println("${age.toString().padStart(5, ' ')}: (${points().toString().padStart(5, ' ')}) ${state.joinToString("")}")

    private fun points() = state.mapIndexed { index, c -> if (c == '#') index - this.index else 0 }.sum()
}

fun main(args: Array<String>) = Day12().solve()