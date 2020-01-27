package net.koffeman.aoc.`2017`

import org.junit.Assert
import org.junit.Test

class Day3Test {

    val day3 = Day3()
    val input = """1
12
23
1024
    """.trimMargin()

    @Test
    fun testPart1() {
        Assert.assertEquals(listOf(0,3,2,31), day3.part1(input.lines()))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(listOf(null,23,25,1968), day3.part2(input.lines()))
    }
}
