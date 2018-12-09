package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

internal class Day9Test {

    val day9 = Day9()
    val input = """9 players; last marble is worth 25 points
10 players; last marble is worth 1618 points
13 players; last marble is worth 7999 points
17 players; last marble is worth 1104 points
21 players; last marble is worth 6111 points
30 players; last marble is worth 5807 points"""

    @Test
    fun testPart1() {
        Assert.assertEquals(listOf(32L, 8317L, 146373L, 2764L, 54718L, 37305L), day9.part1(input.lines()))
    }
}