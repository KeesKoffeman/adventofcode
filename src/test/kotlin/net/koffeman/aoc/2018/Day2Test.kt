package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

internal class Day2Test {

    val day2 = Day2()

    @Test
    fun testPart1() {
        Assert.assertEquals(12, day2.part1(listOf(
                "abcdef",   // 1
                "bababc",   // 3 + 2
                "abbcde",   // 2
                "abcccd",   // 3
                "aabcdd",   // 2
                "abcdee",   // 2
                "ababab"    // 3
        )))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals("fgij", day2.part2(listOf(
            "abcde",
            "fghij",
            "klmno",
            "pqrst",
            "fguij",
            "axcye",
            "wvxyz"
        )))
    }
}