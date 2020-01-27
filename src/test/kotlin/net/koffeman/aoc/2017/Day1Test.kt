package net.koffeman.aoc.`2017`

import org.junit.Assert
import org.junit.Test

class Day1Test {

    val day1 = Day1()
    val input = """1122
1111
1234
91212129
    """.trimIndent()

    @Test
    fun testPart1() {
        Assert.assertEquals(listOf(3, 4, 0, 9), day1.part1(input.lines()))
    }

    val input2 = """1212
1221
123425
123123
12131415
    """.trimIndent()

    @Test
    fun testPart2() {
        Assert.assertEquals(listOf(6, 0, 4, 12, 4), day1.part2(input2.lines()))
    }
}