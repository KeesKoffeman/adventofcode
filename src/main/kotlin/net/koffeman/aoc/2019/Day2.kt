package net.koffeman.aoc.`2019`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day2 : AdventOfCodePuzzle {

    var noun = 12
    var verb = 2

    var output = 19690720

    override fun part1(input: List<String>) = AdventOfCodePuzzle.commaSeparatedIntsToListOfInt(input).map { list -> Program(list, noun, verb).execute() }.map { program -> program.output.toList() }

    override fun part2(input: List<String>) = AdventOfCodePuzzle.commaSeparatedIntsToListOfInt(input).map { list -> Program(list, noun, verb).execute(output) }
}

class Program(private val instructions: List<Int>, private var noun : Int, private var verb : Int) {

    var output = instructions.toMutableList()
    var pointer = 0
    var done = false

    fun execute(): Program {

        reset()

        output[1] = noun
        output[2] = verb

        while (!done) {
            operate()
            pointer+=4
        }
        return this
    }

    fun execute(expected: Int): Int {
        for (n in 0..99) {
            for (v in 0..99) {
                noun = n
                verb = v
                if (execute().output[0] == expected) {
                    return 100 * n + v
                }
            }
        }
        return 0
    }

    private fun operate() {
        val opCode = output[pointer]

        when (opCode) {
            1  -> output[output[pointer+3]] = output[output[pointer+1]] + output[output[pointer+2]]
            2  -> output[output[pointer+3]] = output[output[pointer+1]] * output[output[pointer+2]]
            99 -> done = true
        }
    }

    private fun reset() {
        output = instructions.toMutableList()
        pointer = 0
        done = false
    }
}

fun main(args: Array<String>) = Day2().solve()
