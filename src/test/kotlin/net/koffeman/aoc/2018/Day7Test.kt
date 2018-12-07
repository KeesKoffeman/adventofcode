package net.koffeman.aoc.`2018`

import org.junit.Assert
import org.junit.Test

internal class Day7Test {

    val day7= Day7(2, 0)
    val input = """Step C must be finished before step A can begin.
Step C must be finished before step F can begin.
Step A must be finished before step B can begin.
Step A must be finished before step D can begin.
Step B must be finished before step E can begin.
Step D must be finished before step E can begin.
Step F must be finished before step E can begin."""

    @Test
    fun testPart1() {
        Assert.assertEquals("CABDFE", day7.part1(input.lines().sorted().reversed()))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(15, day7.part2(input.lines()))
    }
}