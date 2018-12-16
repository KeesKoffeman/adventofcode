package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

class Day16Test {

    private val day16 = Day16()
    private val input = """Before: [3, 2, 1, 1]
9 2 1 2
After:  [3, 2, 2, 1]
"""

    @Test
    fun testPart1() {
        Assert.assertEquals(1, day16.part1(input.lines()))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals("", day16.part2(input.lines()))
    }
}
