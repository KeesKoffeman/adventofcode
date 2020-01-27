package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

class Day18Test {

    private val day18 = Day18()
    private val input = """.#.#...|#.
.....#|##|
.|..|...#.
..|#.....#
#.#|||#|#|
...#.||...
.|....|...
||...#|.#|
|.||||..|.
...#.|..|."""

    @Test
    fun testPart1() {
        Assert.assertEquals(1147, day18.part1(input.lines()))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals("", day18.part2(input.lines()))
    }
}
