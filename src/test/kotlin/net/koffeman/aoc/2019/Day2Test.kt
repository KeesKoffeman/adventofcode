package net.koffeman.aoc.`2019`

import org.junit.Assert
import org.junit.Test

class Day2Test {

    private val day2 = Day2()
    private val input = ""

    @Test
    fun testPart1() {

        day2.noun = 3
        day2.verb = 0

        Assert.assertEquals(listOf(2,3,0,6,99), day2.part1(listOf("2,3,0,3,99"))[0])

        day2.noun = 0

        Assert.assertEquals(listOf(2,0,0,0,99), day2.part1(listOf("1,0,0,0,99"))[0])

        day2.noun = 4
        day2.verb = 4

        Assert.assertEquals(listOf(2,4,4,5,99,9801), day2.part1(listOf("2,4,4,5,99,0"))[0])

        day2.noun = 1
        day2.verb = 1

        Assert.assertEquals(listOf(30,1,1,4,2,5,6,0,99), day2.part1(listOf("1,1,1,4,99,5,6,0,99"))[0])
    }

    @Test
    fun testPart2() {
        day2.output = 9706670
        Assert.assertEquals(1202, day2.part2(listOf("1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,10,1,19,1,5,19,23,1,23,5,27,2,27,10,31,1,5,31,35,2,35,6,39,1,6,39,43,2,13,43,47,2,9,47,51,1,6,51,55,1,55,9,59,2,6,59,63,1,5,63,67,2,67,13,71,1,9,71,75,1,75,9,79,2,79,10,83,1,6,83,87,1,5,87,91,1,6,91,95,1,95,13,99,1,10,99,103,2,6,103,107,1,107,5,111,1,111,13,115,1,115,13,119,1,13,119,123,2,123,13,127,1,127,6,131,1,131,9,135,1,5,135,139,2,139,6,143,2,6,143,147,1,5,147,151,1,151,2,155,1,9,155,0,99,2,14,0,0"))[0])
    }
}