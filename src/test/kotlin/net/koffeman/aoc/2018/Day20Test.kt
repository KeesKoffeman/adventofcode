package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

class Day20Test {

    private val day20 = Day20()
    private val input = "^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN\$"

    @Test
    fun testPart1() {
        Assert.assertEquals(23, day20.part1(input.lines()))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals("", day20.part2(input.lines()))
    }
}
