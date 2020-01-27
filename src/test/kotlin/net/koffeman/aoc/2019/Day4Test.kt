package net.koffeman.aoc.`2019`

import org.junit.Assert
import org.junit.Test

class Day4Test {

    private val day4 = Day4()
    private val input = "111111-111111"

    @Test
    fun testPart1() {
        Assert.assertEquals(1, day4.part1(input.lines()))
    }

    private val input2 = "112233-112233"
    private val input3 = "123444-123444"
    private val input4 = "111122-111122"

    @Test
    fun testPart2() {
        Assert.assertEquals(1, day4.part2(input2.lines()))
        Assert.assertEquals(0, day4.part2(input3.lines()))
        Assert.assertEquals(1, day4.part2(input4.lines()))
    }
}
