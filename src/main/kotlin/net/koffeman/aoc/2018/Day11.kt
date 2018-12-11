package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day11 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = Grid(input.first().toInt()).largestFuelSquare(2).let { "${it.start.x},${it.start.y}" }
    override fun part2(input: List<String>) = Grid(input.first().toInt()).largestFuelSquare(300).let { "${it.start.x},${it.start.y},${it.size}" }

    class Grid(private val serialNumber:Int) {

        val map : MutableMap<Int,MutableMap<Int, FuelCell>> = mutableMapOf()

        init { (1 .. 300).forEach { x -> (1 .. 300).forEach { y ->
                    val rackId = x + 10
                    val powerLevel = (((((rackId * y) + serialNumber) * rackId) / 100) % 10) - 5
                    map.computeIfAbsent(x) { mutableMapOf() }[y] = FuelCell(x,y,rackId,powerLevel)
                } }
        }

        fun largestFuelSquare(maxSize : Int) : FuelSquare {
            return map.values
                    .flatMap { it.values }
                    .flatMap { var powerlLevelSum = 0
                        (0 .. Math.min(maxSize, (300-Math.max(it.x, it.y)))).map { size ->
                                (it.x .. it.x + size).forEach { dx -> powerlLevelSum += map[dx]!![it.y + size]!!.powerLevel }
                                (it.y until it.y + size).forEach { dy -> powerlLevelSum += map[it.x + size]!![dy]!!.powerLevel }
                            FuelSquare(it, size+1, powerlLevelSum)
                            }
                    }
                    .maxBy { it.powerLevel }!!
        }
    }

    data class FuelCell(val x: Int, val y: Int, val rackId: Int, val powerLevel: Int)
    data class FuelSquare(val start : FuelCell, val size:Int, val powerLevel: Int)
}

fun main(args: Array<String>) = Day11().solve()