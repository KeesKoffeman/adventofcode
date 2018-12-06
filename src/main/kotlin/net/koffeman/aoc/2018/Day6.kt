package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day6 : AdventOfCodePuzzle {

    override fun part1(input: List<String>): Any = Grid.from(input).largestArea()

    override fun part2(input: List<String>): Any = Grid.from(input).region(if (input.size < 20) 32 else 10000 ).size

    data class Grid(val coords : Set<Coordinate>) {

        companion object { fun from(input : List<String>) : Grid = Grid(input.map { s -> Coordinate.from(s) }.toSet()) }

        fun region(threshold : Int) : Set<Coordinate> = allCoords().map { coordinate -> coordinate to coordinate.totalDistance(coords) }.filter { pair -> pair.second < threshold }.map { pair -> pair.first }.toSet()

        fun largestArea(): Int {
            val fromTo = allCoords().map { coordinate -> coordinate to coordinate.closest(coords) }.filter { pair -> pair.second != null }
            val infinites = fromTo.filter { pair -> pair.first.x == minX() || pair.first.x == maxX() || pair.first.y == minY() || pair.first.y == maxY() }.map { pair -> pair.second }.toSet()
            return fromTo.filter { pair -> !infinites.contains(pair.second) }.groupingBy { pair -> pair.second }.eachCount().maxBy { entry -> entry.value }?.value!!
        }

        private fun maxX() = coords.maxBy { c -> c.x }!!.x
        private fun minX() = coords.minBy { c -> c.x }!!.x
        private fun minY() = coords.minBy { c -> c.y }!!.y
        private fun maxY() = coords.maxBy { c -> c.y }!!.y
        private fun allCoords() : Set<Coordinate> = (minX()..maxX()).map { x -> (minY()..maxY()).map { y -> Coordinate(x,y) }}.flatten().toSet()
    }

    data class Coordinate(val x:Int, val y:Int) {
        companion object { fun from(s:String) : Coordinate = "^([0-9]+), ([0-9]+)$".toRegex().find(s)?.destructured?.let { (x, y) -> Coordinate(x.toInt(), y.toInt()) }!! }
        fun distance(other:Coordinate) = Math.abs(this.x-other.x)+Math.abs(this.y-other.y)
        fun closest(coords: Set<Day6.Coordinate?>) : Coordinate? = coords.map { coordinate -> coordinate to coordinate?.distance(this) }.sortedBy { pair -> pair.second }.reduce { a,b -> if (a.second!! < b.second!!) a else Pair(null, -1)  }.first
        fun totalDistance(coords: Set<Day6.Coordinate>) : Int  = coords.map { c -> distance(c) }.sum()
    }
}

fun main() = Day6().solve()