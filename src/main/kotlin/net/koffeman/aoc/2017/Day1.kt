package net.koffeman.aoc.`2017`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day1 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = input.map { it.checksum() }

    override fun part2(input: List<String>) = input.map { it.checksum2() }

    private fun String.checksum() = (this + this.first()).asSequence().windowed(2).filter { it.first() == it.last() }.map { it.first().toString().toInt() }.sumBy { it }

    private fun String.checksum2(): Any {

        var checksum = 0

        (0 until this.length).forEach{
            if ( this[it] == this[(it + (length/2)) % this.length]) {
                checksum += this[it].toString().toInt()
            }
        }

        return checksum
    }

}

fun main(args: Array<String>) = Day1().solve()