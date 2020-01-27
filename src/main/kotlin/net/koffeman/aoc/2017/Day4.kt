package net.koffeman.aoc.`2017`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day4 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = input.map { it.validatePassPhrase() }.count { it }
    override fun part2(input: List<String>) = input.map { it.validatePassPhrase2() }.count { it }
}

private fun String.validatePassPhrase() = with (this.split("\\s".toRegex())) { size == toSet().size }
private fun String.validatePassPhrase2()= with (this.split("\\s".toRegex())) { filterIndexed { index, it -> filterIndexed { index2, _ -> index!=index2 }.any { s -> s.toList().sorted() == it.toList().sorted() } }.isEmpty() }

fun main(args: Array<String>) = Day4().solve()