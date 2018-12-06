package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day1 : AdventOfCodePuzzle {

    override fun part1(input: List<String>): Int = input.map { it.toInt() }.fold(0) { sum, element -> sum + element }

    override fun part2(input: List<String>): Int {
        var frequencies = mutableSetOf(0)
        return fun(): Int {
            return input.map { it.toInt() }.repeat().fold(0) { sum, element ->
                val frequency = sum + element
                if (frequencies.contains(frequency)) return frequency
                frequencies.add(frequency)
                frequency
            }
        }()
    }

    private fun <T> List<T>.repeat(): Sequence<T> = generateSequence(0) { (it + 1) % this.size }.map(::get)
}

fun main(args: Array<String>) = Day1().solve()