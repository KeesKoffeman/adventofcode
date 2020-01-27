package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

class Day13Test {

    private val day13 = Day13()
    private val input = """/->-\
|   |  /----\
| /-+--+-\  |
| | |  | v  |
\-+-/  \-+--/
  \------/   """

    @Test
    fun testPart1() {
        Assert.assertEquals(Pair(7,3), day13.part1(input.lines()))
    }

    val input2 = """/>-<\
|   |
| /<+-\
| | | v
\>+</ |
  |   ^
  \<->/"""

    @Test
    fun testPart2() {
        Assert.assertEquals(Pair(6,4), day13.part2(input2.lines()))
    }
}
