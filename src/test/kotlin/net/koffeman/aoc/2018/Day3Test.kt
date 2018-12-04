package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

internal class Day3Test {

    val day3 = Day3()

    @Test
    fun testPart1() {
        Assert.assertEquals(4, day3.part1(listOf(
                "#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2"
        )))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(3, day3.part2(listOf(
                "#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2"
        )))
    }
}