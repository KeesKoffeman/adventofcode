package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

class Day14Test {

    private val day14 = Day14()
    private val input = ""

    @Test
    fun testPart1() {
        Assert.assertEquals("0124515891", day14.part1(listOf("5")))
        Assert.assertEquals("9251071085", day14.part1(listOf("18")))
        Assert.assertEquals("5941429882", day14.part1(listOf("2018")))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(9, day14.part2(listOf("51589")))
        Assert.assertEquals(5, day14.part2(listOf("01245")))
        Assert.assertEquals(18, day14.part2(listOf("92510")))
        Assert.assertEquals(2018, day14.part2(listOf("59414")))
    }
}
