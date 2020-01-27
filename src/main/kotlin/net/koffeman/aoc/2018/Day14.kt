package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day14 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = Recipe().predict(input.first().toInt())

    override fun part2(input: List<String>) = Recipe().predict(input.first())
}

class Recipe(var list : MutableList<Int> = mutableListOf(3,7)) {

    var elf2:Int = 1
    var elf1:Int = 0

    fun predict(times: Int): Any {
        while(list.size < times+10) {
            cook()
        }
        return list.subList(times, times+10).joinToString("")
    }

    fun predict(times: String) : Any {

        val fetch : List<Int> = times.asSequence().map { c -> c.toString().toInt() }.toList()

        while (true) {
            cook()

            val last = list.takeLast(times.length+1)
            if (last.subList(0, last.size-1) == fetch) {
                return list.size-(times.length+1)
            }
            if (last.subList(1, last.size) == fetch) {
                return list.size-times.length
            }
        }
    }

    private fun cook() {
        list.addAll((list[elf1] + list[elf2]).toString().asSequence().map { it.toString().toInt() })
        if (list.size > 4) {
            elf1 = (elf1 + list[elf1] + 1) % list.size
            elf2 = (elf2 + list[elf2] + 1) % list.size
        }
    }


}

fun main(args: Array<String>) = Day14().solve()