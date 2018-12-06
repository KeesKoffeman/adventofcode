package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day5 : AdventOfCodePuzzle {

    override fun part1(input: List<String>): Any = input.first().polymer()

    override fun part2(input: List<String>): Any =
        ('a'..'z')
            .map { c -> input.first().filter { c2 -> !c2.equals(c, true) } }
            .map { s -> s.polymer() }
            .minBy { s -> s }!!

    fun String.polymer() : Int {

        val reactions = ('a'..'z').map { c -> listOf("$c${c.toUpperCase()}", "${c.toUpperCase()}$c") }.flatten()
        var before: String
        var result = this

        do {
            before = result
            reactions.forEach { reaction: String -> result = result.replace(reaction, "") }
        } while (before != result)

        return result.length
    }
}

fun main() = Day5().solve()