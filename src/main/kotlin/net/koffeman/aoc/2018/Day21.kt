package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day21 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = ProgramWithInstructionPointers.from(input.first()[4].toString().toInt(), input.subList(1, input.size)).compute(Int.MAX_VALUE + 615138291)

    override fun part2(input: List<String>) = ""
}

fun main(args: Array<String>) = Day21().solve()
