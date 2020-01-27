package net.koffeman.aoc.`2017`

import org.junit.Assert
import org.junit.Test

class Day2Test {

    val day2 = Day2()
    val input = """5 1 9 5
7 5 3
2 4 6 8"""

    @Test
    fun testPart1() {
        Assert.assertEquals(18, day2.part1(input.lines()))
    }

    val input2 = """5 9 2 8
9 4 7 3
3 8 6 5"""

    @Test
    fun testPart2() {
        Assert.assertEquals(9, day2.part2(input2.lines()))
    }
}
