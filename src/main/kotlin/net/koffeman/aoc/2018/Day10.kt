package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day10 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = ElfMessageInTheSky.from(input).await().pretty()

    override fun part2(input: List<String>) = "Waited for ${ElfMessageInTheSky.from(input).await().cycles} seconds for message to appear magically in the sky."

    data class ElfMessageInTheSky(val points:List<Point>, var cycles : Int = 0) {
        companion object {
            fun from(input: List<String>) = ElfMessageInTheSky(input.map { "position=<\\s*(-?[0-9]+),\\s*(-?[0-9]+)> velocity=<\\s*(-?[0-9]+),\\s*(-?[0-9]+)>".toRegex().find(it)!!.destructured.let { (x,y,xVelocity, yVelocity)  -> Point(x.toInt(),y.toInt(), xVelocity.toInt(), yVelocity.toInt()) } })
        }

        fun await() : ElfMessageInTheSky {

            do {
                val size = Math.abs(maxY() - minY())
                forward()
                val newSize = Math.abs(maxY() - minY())
            } while ( newSize < size)

            rewind()
            return this
        }

        private fun forward() {
            points.forEach { point -> point.forward() }
            cycles++
        }

        private fun rewind() {
            points.forEach { point -> point.rewind() }
            cycles--
        }

        fun pretty() : String {
            val str = StringBuilder()
            (minY()..maxY()).forEach { y ->
                str.append("\n")
                (minX()..maxX()).forEach { x ->
                    when {
                        points.any { it.x == x && it.y == y } -> str.append("#")
                        else -> str.append(" ")
                    }
                }
            }
            return str.toString()
        }

        private fun maxX() = points.maxBy { it.x }!!.x
        private fun minX() = points.minBy { it.x }!!.x
        private fun maxY() = points.maxBy { it.y }!!.y
        private fun minY() = points.minBy { it.y }!!.y
    }

    data class Point(var x:Int, var y:Int, val xVelocity:Int, val yVelocity:Int) {
        fun forward() {
            x += xVelocity
            y += yVelocity
        }
        fun rewind() {
            x -= xVelocity
            y -= yVelocity
        }
    }
}

fun main(args: Array<String>) = Day10().solve()