package net.koffeman.aoc.`2017`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day3 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = input.map { Grid(it.toInt()).steps() }

    override fun part2(input: List<String>) = input.map { Grid(it.toInt()).firstLarger() }

    class Grid(private val size:Int) {

        enum class Directions { EAST, WEST, NORTH, SOUTH }
        private val nodes : MutableMap<Int, MutableMap<Int,Node>> = mutableMapOf()
        private val ids : MutableSet<Int> = mutableSetOf()

        fun steps(): Any {

            val first = Node(1, size / 4, size / 4)
            add(first)

            var x : MutableSet<Int> = mutableSetOf()
            var y : MutableSet<Int> = mutableSetOf()

            var last = first
            var direction = Directions.WEST

            (1 until size).forEach{ _ ->

                x.add(last.x)
                y.add(last.y)

                val minX = x.min()!!
                val maxX = x.max()!!
                val minY = y.min()!!
                val maxY = y.max()!!

                last = when (direction) {
                    Directions.NORTH -> create(last.x,last.y-1)
                    Directions.WEST -> create(last.x+1,last.y)
                    Directions.SOUTH -> create(last.x,last.y+1)
                    Directions.EAST -> create(last.x-1,last.y)
                }

                add(last)

                direction = when (direction) {
                    Directions.EAST  -> if (last.x < minX) Directions.SOUTH else Directions.EAST
                    Directions.WEST  -> if (last.x > maxX) Directions.NORTH else Directions.WEST
                    Directions.NORTH -> if (last.y < minY) Directions.EAST else Directions.NORTH
                    Directions.SOUTH -> if (last.y > maxY) Directions.WEST else Directions.SOUTH
                }
            }

            return Math.abs(first.x-last.x) + Math.abs(first.y-last.y)
        }

        private fun add(node : Node) {
            nodes.computeIfAbsent(node.x) { mutableMapOf() }[node.y] = node
        }

        private fun create(x: Int, y: Int): Node {
            val id = (x - 1..x + 1).map { otherX -> (y - 1..y + 1).map { otherY -> otherX to otherY } }
                    .flatten()
                    .mapNotNull { get(it.first,it.second) }
                    .sumBy { it.id }
            ids.add(id)
            return Node(id,x,y)
        }

        private fun get(x: Int, y: Int): Node? {
            if (nodes.computeIfAbsent(x){ mutableMapOf() }.containsKey(y)) {
                return nodes.computeIfAbsent(x){ mutableMapOf() }[y]
            }
            return null
        }

        fun firstLarger(): Int? {
            steps()
            return ids.sorted().firstOrNull { it>size }
        }
    }

    data class Node(val id:Int, val x:Int, val y:Int)

}

fun main(args: Array<String>) = Day3().solve()