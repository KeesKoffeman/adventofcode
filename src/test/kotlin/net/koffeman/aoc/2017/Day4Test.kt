package net.koffeman.aoc.`2017`

import org.junit.Assert
import org.junit.Test

class Day4Test {

    val day4 = Day4()
    val input = """aa bb cc dd ee
aa bb cc dd aa
aa bb cc dd aaa""".trimIndent()

    @Test
    fun testPart1() {
        Assert.assertEquals(2, day4.part1(input.lines()))
    }

    val input2 = """abcde fghij
abcde xyz ecdab
a ab abc abd abf abj
iiii oiii ooii oooi oooo
oiii ioii iioi iiio is""".trimIndent()

    @Test
    fun testPart2() {
        Assert.assertEquals(3, day4.part2(input2.lines()))
    }
}
