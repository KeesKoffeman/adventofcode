package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class Day11Test {

    private val day11 = Day11()

    @Test
    fun testPart1() {
        Assert.assertEquals("33,45", day11.part1(listOf("18")))
        Assert.assertEquals("21,61", day11.part1(listOf("42")))
    }

    @Test
    @Ignore // Takes too long
    fun testPart2() {
        Assert.assertEquals("90,269,16", day11.part2(listOf("18")))
        Assert.assertEquals("232,251,12", day11.part2(listOf("42")))
    }
}