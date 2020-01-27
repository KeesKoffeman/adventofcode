package net.koffeman.aoc

import java.io.File
import kotlin.system.exitProcess

val year = args[0].toInt()
val day = args[1].toInt()

if (2015 > year || year > 2020) { println("Invalid year $year."); exitProcess(1) }
if (0 > day || day > 26) { println("Invalid day $day."); exitProcess(1) }

println("Scaffolding files for day ${day} of Advent of Code $year .. ")

val sourceDir = File("src/main/kotlin/net/koffeman/aoc/$year")
sourceDir.mkdir()

val sourceFile = File("src/main/kotlin/net/koffeman/aoc/$year/Day$day.kt")
if (!sourceFile.exists()) {
sourceFile.createNewFile()
    sourceFile.appendText("""package net.koffeman.aoc.`$year`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day$day : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = ""

    override fun part2(input: List<String>) = ""
}

fun main() = Day$day().solve()
""")
    println(" *  Created '${sourceFile.path}'.")
}

val resourceDir = File("src/main/resources/$year")
resourceDir.mkdir()
val resourceFile = File("src/main/resources/$year/Day$day.txt")
if (!resourceFile.exists()) {
    resourceFile.createNewFile()
    println(" *  Created '${resourceFile.path}'.")
}

val testSourceDir = File("src/test/kotlin/net/koffeman/aoc/$year")
testSourceDir.mkdir()
val testSourceFile = File("src/test/kotlin/net/koffeman/aoc/$year/Day${day}Test.kt")
if (!testSourceFile.exists()) {
    testSourceFile.createNewFile()
    testSourceFile.appendText("""package net.koffeman.aoc.`$year`

import org.junit.Assert
import org.junit.Test

class Day${day}Test {

    private val day$day = Day$day()
    private     val input = ""

    @Test
    fun testPart1() {
        Assert.assertEquals("", day$day.part1(input.lines()))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals("", day$day.part2(input.lines()))
    }
}
""")
    println(" *  Created '${testSourceFile.path}'.")
}