package net.koffeman.aoc

import java.io.File

interface AdventOfCodePuzzle {

    fun part1(input: List<String>): Any
    fun part2(input: List<String>): Any

    fun solve() {

        val day = """Day([0-9]+)""".toRegex().find(this.javaClass.name)?.groupValues?.get(1)
        val year = this.javaClass.packageName.split(".").last()
        val input = File(ClassLoader.getSystemResource("$year/Day$day.txt").file).readLines()

        println("Solving puzzle for day $day of $year .. ")
        println("Part 1: ${part1(input)}")
        println("Part 2: ${part2(input)}")
    }
}
