package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

internal class Day8Test {

    val day8 = Day8()
    val input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"

    @Test
    fun testPart1() {
        Assert.assertEquals(138, day8.part1(listOf(input)))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(66, day8.part2(listOf(input)))
    }
}