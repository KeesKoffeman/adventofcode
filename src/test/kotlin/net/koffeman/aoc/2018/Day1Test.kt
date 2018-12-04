package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

class Day1Test {

    val day1 = Day1()

    @Test
    fun testPart1() {
        Assert.assertEquals(-1, day1.part1(listOf(1, -2).map { it.toString() }))
        Assert.assertEquals(2, day1.part1(listOf(-1, +3).map { it.toString() }))
        Assert.assertEquals(3, day1.part1(listOf(2, +1).map { it.toString() }))
        Assert.assertEquals(3, day1.part1(listOf(+1, +1, +1).map { it.toString() }))
        Assert.assertEquals(0, day1.part1(listOf(+1, +1, -2).map { it.toString() }))
        Assert.assertEquals(-6, day1.part1(listOf(-1, -2, -3).map { it.toString() }))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(0, day1.part2(listOf( +1, -1).map { it.toString() }))
        Assert.assertEquals(10, day1.part2(listOf(+3, +3, +4, -2, -4).map { it.toString() }))
        Assert.assertEquals(5, day1.part2(listOf(-6, +3, +8, +5, -6).map { it.toString() }))
        Assert.assertEquals(14, day1.part2(listOf(+7, +7, -2, -7, -4).map { it.toString() }))
    }
}
