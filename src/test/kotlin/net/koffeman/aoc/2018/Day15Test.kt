package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

class Day15Test {

    private val day15 = Day15()

    @Test
    fun testPart1_1() {
        Assert.assertEquals(27730, day15.part1("""#######
#.G...#
#...EG#
#.#.#G#
#..G#E#
#.....#
#######""".lines()))
    }

    @Test
    fun testPart1_2() {
        Assert.assertEquals(36334, day15.part1("""#######
#G..#E#
#E#E.E#
#G.##.#
#...#E#
#...E.#
#######""".lines()))
    }

    @Test
    fun testPart1_3() {
        Assert.assertEquals(39514, day15.part1("""#######
#E..EG#
#.#G.E#
#E.##E#
#G..#.#
#..E#.#
#######""".lines()))

        Assert.assertEquals(27755, day15.part1("""#######
#E.G#.#
#.#G..#
#G.#.G#
#G..#.#
#...E.#
#######""".lines()))
    }

    @Test
    fun testPart1_4() {
        Assert.assertEquals(28944, day15.part1("""#######
#.E...#
#.#..G#
#.###.#
#E#G#G#
#...#G#
#######""".lines()))
    }


    @Test
    fun testPart1_5() {
        Assert.assertEquals(18740, day15.part1("""#########
#G......#
#.E.#...#
#..##..G#
#...##..#
#...#...#
#.G...G.#
#.....G.#
#########""".lines()))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals("", day15.part2("""#######
    #.G...#
    #...EG#
    #.#.#G#
    #..G#E#
    #.....#
    #######""".lines()))
    }
}
