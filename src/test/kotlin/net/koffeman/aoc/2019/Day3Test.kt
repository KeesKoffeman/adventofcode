package net.koffeman.aoc.`2019`

import org.junit.Assert
import org.junit.Test

class Day3Test {

    private val day3 = Day3()
    private val input1 = """R75,D30,R83,U83,L12,D49,R71,U7,L72
U62,R66,U55,R34,D71,R55,D58,R83"""
    private val input2 = """R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"""

    @Test
    fun testPart1() {
        Assert.assertEquals(159, day3.part1(input1.lines()))
        Assert.assertEquals(135, day3.part1(input2.lines()))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(610, day3.part2(input1.lines()))
        Assert.assertEquals(410, day3.part2(input2.lines()))
    }
}
