package net.koffeman.aoc.`2019`

import net.koffeman.aoc.AdventOfCodePuzzle
import kotlin.math.abs

class Day3 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = WireCircuit(input).distance()

    override fun part2(input: List<String>) = WireCircuit(input).steps()
}

fun main() = Day3().solve()

class WireCircuit(input: List<String>) {

    private val centralPort = 0 to 0

    private val path1 = translate(input[0])
    private val path2 = translate(input[1])

    fun distance(): Int {
        return intersect()
                .map { intersect -> abs(intersect.first - centralPort.first) to abs(intersect.second - centralPort.second) }
                .map { intersect -> intersect.first + intersect.second }
                .min()!!
    }

    fun steps(): Int {
        val firstIntersection = intersect().first()
        return path1.indexOf(firstIntersection) + path2.indexOf(firstIntersection)
    }

    private fun intersect() = path1.intersect(path2).filter { pair -> pair != centralPort }

    private fun translate(path: String) = path.split(",").fold(mutableListOf(centralPort)) { steps, s -> move(steps, s[0], s.substring(1).toInt()) }

    private fun move(steps: MutableList<Pair<Int, Int>>, direction: Char, velocity: Int) : MutableList<Pair<Int,Int>> {

        var x = steps.last().first
        var y = steps.last().second

        when (direction) {
            'U' -> for (newY in (y-1) downTo (y-velocity)) { steps.add(x to newY)}
            'D' -> for (newY in (y+1)   ..   (y+velocity)) { steps.add(x to newY)}
            'L' -> for (newX in (x-1) downTo (x-velocity)) { steps.add(newX to y)}
            'R' -> for (newX in (x+1)   ..   (x+velocity)) { steps.add(newX to y)}
        }

        return steps
    }
}