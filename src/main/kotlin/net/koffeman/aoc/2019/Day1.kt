package net.koffeman.aoc.`2019`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day1 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = input.map(::calculate).sum()

    override fun part2(input: List<String>) = input.map(::recurse).sum()

    private fun calculate(s: String) = (s.toInt() / 3) - 2

    private fun recurse(s: String): Int {

        var i = s
        var r = 0

        while (calculate(i)>0) {
            val c = calculate(i)
            r += c
            i = c.toString()
        }

        return r
    }
}

fun main() = Day1().solve()
