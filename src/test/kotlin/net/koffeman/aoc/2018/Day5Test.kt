package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

internal class Day5Test {

    val day5 = Day5()
    val input = "dabAcCaCBAcCcaDA"

    @Test
    fun testPart1() {
        Assert.assertEquals("dabCBAcaDA".length, day5.part1(listOf(input)))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(4, day5.part2(listOf(input)))
    }
}