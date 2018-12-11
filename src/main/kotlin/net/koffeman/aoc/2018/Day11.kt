package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day11 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = Grid(input.first().toInt()).select(2).let { "${it.first.x},${it.first.y}" }

    override fun part2(input: List<String>) = Grid(input.first().toInt()).select(20).let { "${it.first.x},${it.first.y},${it.second}" }

    class Grid(private val serialNumber:Int) {

        val map : MutableMap<Int,MutableMap<Int, FuelCell>> = mutableMapOf()

        init {
                (1 .. 300).forEach { x -> (1 .. 300).forEach { y ->
                    val rackId = x + 10
                    val powerLevel = with(((rackId * y) + serialNumber) * rackId) { val str = toString();str[str.length - 3].toString().toInt() - 5 }
                    map.computeIfAbsent(x) { mutableMapOf() }[y] = FuelCell(x,y,rackId,powerLevel)
                } }
        }

        fun select(maxSize : Int) : Pair<FuelCell,Int> {

            val powerLevelPerSquare : MutableMap<Int,Pair<FuelCell, Int>> = mutableMapOf()
            (1 .. 300).forEach { gridY -> (1 .. 300).forEach { gridX ->

                val ceiledMaxSize = Math.min(maxSize, (300-Math.max(gridX, gridY)) )

                (1 .. ceiledMaxSize).forEach { size ->
                    val powerLevel = (gridY..gridY + size).map { sY -> (gridX..gridX + size).map { sX -> sX to sY } }.flatten().sumBy { map[it.first]!![it.second]!!.powerLevel }
                    if (!powerLevelPerSquare.containsKey(powerLevel)) {
                        powerLevelPerSquare[powerLevel] = map[gridX]!![gridY]!! to size+1
                    }
                }
            }}

            return powerLevelPerSquare[powerLevelPerSquare.keys.max()]!!
        }
    }

    data class FuelCell(val x: Int, val y: Int, val rackId: Int, val powerLevel: Int)
}

fun main(args: Array<String>) = Day11().solve()