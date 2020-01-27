package net.koffeman.aoc.`2019`

import org.junit.Assert
import org.junit.Test

class Day1Test {

    private val day1 = Day1()
    private val input = ""

    @Test
    fun testPart1() {
        Assert.assertEquals(2, day1.part1(listOf(12).map { it.toString() }))
        Assert.assertEquals(2, day1.part1(listOf(14).map { it.toString() }))
        Assert.assertEquals(654, day1.part1(listOf(1969).map { it.toString() }))
        Assert.assertEquals(33583, day1.part1(listOf(100756).map { it.toString() }))

    }

    @Test
    fun testPart2() {
        Assert.assertEquals(2, day1.part2(listOf(14).map { it.toString() }))
        Assert.assertEquals(966, day1.part2(listOf(1969).map { it.toString() }))
        Assert.assertEquals(50346, day1.part2(listOf(100756).map { it.toString() }))
    }
}
