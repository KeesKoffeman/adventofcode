package net.koffeman.aoc.`2019`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day4 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) =
            range(input)
                    .filter(::neverDecreases)
                    .filter(::hasAdjecentInts)
                    .count()

    override fun part2(input: List<String>) =
            range(input)
                    .filter(::neverDecreases)
                    .filter(::hasAdjecentInts2)
                    .count()

    private fun neverDecreases(intList: List<Int>) =
            intList.windowed(2).none { l -> (l[0] > l[1]) }

    private fun hasAdjecentInts(intList: List<Int>) =
            intList.windowed(2).any { l -> l[0] == l[1] }

    private fun hasAdjecentInts2(password: List<Int>): Boolean {

        var countedPwd = mutableListOf<Pair<Int,Int>>()
        password.forEach { i: Int ->
            if (countedPwd.isNotEmpty() && countedPwd.last().first == i) {
                countedPwd[countedPwd.lastIndex] = i to countedPwd.last().second + 1
            } else {
                countedPwd.add(i to 1)
            }
        }

        return countedPwd.any { p -> p.second == 2 }
    }

    private fun range(input: List<String>): List<List<Int>> {
        val split = input[0].split("-")
        val intRange = split[0].toInt()..split[1].toInt()
        return intRange.map { i ->  i.toString().toList().map { c -> c.toString().toInt() }}
    }
}

fun main() = Day4().solve()