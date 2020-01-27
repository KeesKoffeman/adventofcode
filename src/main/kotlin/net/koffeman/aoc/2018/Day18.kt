package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day18 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = LumberArea.from(input).compute(10)

    override fun part2(input: List<String>) = LumberArea.from(input).computeWithCycleDetection(1000000000)
}

class LumberArea(private val acres: List<Acre>) {

    var minute : Int = 0
    val map : MutableMap<Int,MutableMap<Int,Acre>> = mutableMapOf()

    init {
        acres.forEach { map.computeIfAbsent(it.x) { mutableMapOf()}[it.y] = it }
    }

    fun compute(minutes: Int): Int {
        (1..minutes).forEach { minute ->
            acres.map { it to adjecent(it) }.forEach { it.first.mutate(it.second) }
            this.minute = minute
        }

        val scores = acres.groupingBy { it.current() }.eachCount()
        return (scores['|']?:0) * (scores['#']?:0)
    }

    fun computeWithCycleDetection(minutes: Int): Any {
        compute(3000)
        return compute((minutes - 3000) % 7000)
    }

    private fun adjecent(acre: Acre): Map<Char,Int> {

        var adjecent = mutableListOf<Acre>()
        (Math.max(0,acre.x-1) .. Math.min(map.keys.size-1, acre.x+1)).forEach { x ->
            (Math.max(0, acre.y-1) .. Math.min(map[x]!!.keys.size-1, acre.y+1)).forEach { y ->
                val other = map[x]!![y]!!
                if (other != acre) {
                    adjecent.add(other)
                }
            }
        }
        return adjecent.groupingBy { it.current() }.eachCount()
    }

    private fun print() {
        println("# Minute $minute #")
        println("------------")
        (0 until map[0]!!.size).forEach { y ->
            (0 until map.size).forEach { x ->
                print(map[x]!![y]!!.current())
            }
            println()
        }
        println("------------")
    }

    companion object {
        fun from(input: List<String>) =  LumberArea(input.mapIndexed { y, str -> str.mapIndexed{ x, c ->  Acre(x,y,c)} }.flatten())
    }

    class Acre(val x: Int, val y: Int, c: Char) {
        val state : MutableList<Char> = mutableListOf(c)
        fun current() : Char = state.last()
        fun mutate(adjecent: Map<Char,Int>) {
            state.add(when (current()) {
                '.' -> if (adjecent['|']?:0 >= 3) '|' else '.'
                '|' -> if (adjecent['#']?:0 >= 3) '#' else '|'
                '#' -> if (adjecent['#']?:0 >= 1 && adjecent['|']?:0 >= 1) '#' else '.'
                else -> current()
            })
        }

    }

}

fun main(args: Array<String>) = Day18().solve()
