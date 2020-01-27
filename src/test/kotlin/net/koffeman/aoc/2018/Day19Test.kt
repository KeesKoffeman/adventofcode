package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class Day19Test {

    private val day19 = Day19()
    private val input = """#ip 0
seti 5 0 1
seti 6 0 2
addi 0 1 0
addr 1 2 3
setr 1 0 0
seti 8 0 4
seti 9 0 5"""

    @Test
    fun testPart1() {
        Assert.assertEquals(6, day19.part1(input.lines()))
    }

    @Test
    @Ignore
    fun testPart2() {
        Assert.assertEquals(6, day19.part2(input.lines()))
    }
}
