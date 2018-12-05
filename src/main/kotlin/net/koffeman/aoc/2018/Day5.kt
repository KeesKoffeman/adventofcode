package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day5 : AdventOfCodePuzzle {

    override fun part1(input: List<String>): Any {
        return input.first().polymer()
    }

    override fun part2(input: List<String>): Any {
        return ('a'..'z')
                .asSequence()
                .map { c -> input.first().filter { c2 -> !c2.equals(c, true) } }
                .map { s -> s.polymer() }
                .minBy { s -> s }!!
    }

    fun String.polymer() : Int {

        var polymer = this
        var chunkToRemove: String?

        do {
            chunkToRemove = polymer.findFirstReaction()
            if (chunkToRemove != null) {
                val indexOfChunkToRemove = polymer.indexOf(string = chunkToRemove)
                polymer = polymer.removeRange(indexOfChunkToRemove, indexOfChunkToRemove + 2)
            }

        } while (chunkToRemove!=null)

        return polymer.length
    }

    private fun String.findFirstReaction(): String? = this.windowed(2).firstOrNull { s -> s.first().equalsWithDifferingCase(s.last()) }

    private fun Char.equalsWithDifferingCase(another : Char) : Boolean = this.equals(another, true) && !this.equals(another)
}

fun main() = Day5().solve()