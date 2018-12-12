package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

class Day12Test {

    private val day12 = Day12()
    private val input = """initial state: #..#.#..##......###...###

...## => #
..#.. => #
.#... => #
.#.#. => #
.#.## => #
.##.. => #
.#### => #
#.#.# => #
#.### => #
##.#. => #
##.## => #
###.. => #
###.# => #
####. => #"""

    @Test
    fun testPart1() {
        Assert.assertEquals(325, day12.part1(input.lines()))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(240, day12.part2(input.lines()))
    }

    @Test
    fun testPart3() {
//        Assert.assertEquals(Pots(input.lines()).grow(50000), Pots(input.lines()).part2(50000))
        Assert.assertEquals(Pots(input.lines()).grow(666), Pots(input.lines()).part2(666))
//        Assert.assertEquals(Pots(input.lines()).grow(64567), Pots(input.lines()).part2(64567))
    }
}
