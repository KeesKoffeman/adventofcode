package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day2 : AdventOfCodePuzzle {

    override fun part1(input: List<String>): Any {
        return input
                .map { s -> s.characterFrequency().map { entry -> entry.value }.distinct().filter { frequency -> frequency > 1 }}
                .flatten()
                .joinToString("")
                .characterFrequency()
                .map { entry -> entry.value }
                .reduce { a, b ->  a * b }
    }

    override fun part2(input: List<String>): Any {
        return input
                .map { s1 -> input.filter { s2 -> s1 != s2 }.map { s2 -> Pair(s1, s2) } }
                .flatten()
                .map { pair -> pair.first.longestCommonSubstring(pair.second) }
                .sortedByDescending { s -> s.length }
                .first()
    }

    private fun String.characterFrequency() : Map<Char, Int> {
        return this.asSequence().groupingBy { c -> c }.eachCount()
    }

    private fun String.longestCommonSubstring(other : String) : String {
        var lcs = ""
        for (i in 0 until Math.min(this.length, other.length)) {
            if (this[i] == other[i]) {
                lcs += this[i]
            }
        }
        return lcs
    }
}

fun main (args: Array<String>) = Day2().solve()