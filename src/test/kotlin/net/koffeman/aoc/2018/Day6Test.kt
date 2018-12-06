package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

internal class Day6Test {

    val day6= Day6()
    val input = """1, 1
1, 6
8, 3
3, 4
5, 5
8, 9"""

    @Test
    fun testPart1() {
        Assert.assertEquals(17, day6.part1(input.lines()))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(16, day6.part2(input.lines()))
    }
}