package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day6 : AdventOfCodePuzzle {

    override fun part1(input: List<String>): Any = Grid.from(input).largestInfiniteAreaSize()

    override fun part2(input: List<String>): Any = Grid.from(input).region(if (input.size < 20) 32 else 10000 ).size

    data class Grid(val locations : Set<Coordinate>) {
        companion object { fun from(input : List<String>) : Grid = Grid(input.map { s -> Coordinate.from(s) }.toSet()) }
        fun region(threshold : Int) : Set<Coordinate> = allCoordinates().map { coordinate -> coordinate to coordinate.totalDistance(locations) }.filter { pair -> pair.second < threshold }.map { pair -> pair.first }.toSet()
        private fun areas() = allCoordinates().map { coordinate -> coordinate to coordinate.closest(locations) }.filter { pair -> pair.second != null }
        fun largestInfiniteAreaSize(): Int = with (areas()) {
                val infinites = filter { pair -> isBoundary(pair.first) }.map { pair -> pair.second }.toSet()
                filter { pair -> !infinites.contains(pair.second) }.groupingBy { pair -> pair.second }.eachCount().maxBy { entry -> entry.value }?.value!!
        }
        private fun maxX() = locations.maxBy { c -> c.x }!!.x
        private fun minX() = locations.minBy { c -> c.x }!!.x
        private fun minY() = locations.minBy { c -> c.y }!!.y
        private fun maxY() = locations.maxBy { c -> c.y }!!.y
        private fun allCoordinates() : Set<Coordinate> = (minX()..maxX()).map { x -> (minY()..maxY()).map { y -> Coordinate(x,y) }}.flatten().toSet()
        private fun isBoundary(coordinate: Coordinate) : Boolean = coordinate.x == minX() || coordinate.x == maxX() || coordinate.y == minY() || coordinate.y == maxY()
    }

    data class Coordinate(val x:Int, val y:Int) {
        companion object { fun from(s:String) : Coordinate = "^([0-9]+), ([0-9]+)$".toRegex().find(s)?.destructured?.let { (x, y) -> Coordinate(x.toInt(), y.toInt()) }!! }
        private fun distance(other:Coordinate) = Math.abs(this.x-other.x)+Math.abs(this.y-other.y)
        fun closest(coordinates: Set<Day6.Coordinate?>) : Coordinate? = coordinates.map { coordinate -> coordinate to coordinate?.distance(this) }.sortedBy { pair -> pair.second }.reduce { a, b -> if (a.second!! < b.second!!) a else Pair(null, -1)  }.first
        fun totalDistance(coordinates: Set<Day6.Coordinate>) : Int  = coordinates.map { c -> distance(c) }.sum()
    }
}

fun main() = Day6().solve()