package net.koffeman.aoc.`2019`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day5 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = IntCodeComputer(AdventOfCodePuzzle.commaSeparatedIntsToListOfInt(input)[0], 1).execute()

    var intCodeComputerInput = 5

    override fun part2(input: List<String>) =  IntCodeComputer(AdventOfCodePuzzle.commaSeparatedIntsToListOfInt(input)[0], intCodeComputerInput).execute()
}

fun main() = Day5().solve()

class IntCodeComputer(private val instructions: List<Int>, val input: Int) {

    var instr = instructions.toMutableList()
    var pointer = 0
    var output = mutableListOf<Int>()

    fun execute(): Int {

        while (!instr[pointer].toString().endsWith("99")) {
            operate()
        }

//        if(output.dropLast(1).toSet() != setOf(0)) throw RuntimeException("Kresj")

        println(output)

        return output.last()
    }

    enum class Mode {
        IMMEDIATE,
        POSITION
    }

    private fun operate() {

        val instruction = instr[pointer].toString().padStart(5,'0')

        var opCode =     instruction.substring(3,5).toInt()
        //        var param3 = if (instruction[0] == '0') Mode.POSITION else Mode.IMMEDIATE

        when (opCode) {
            1  -> instr[instr[pointer+3]] = parameter1(instruction) + parameter2(instruction)
            2  -> instr[instr[pointer+3]] = parameter1(instruction) * parameter2(instruction)
            3  -> instr[instr[pointer+1]] = input
            4  -> output.add(instr[instr[pointer+1]])

            5  -> pointer = if (parameter1(instruction) > 0 ) parameter2(instruction) else pointer+3
            6  -> pointer = if (parameter1(instruction) == 0) parameter2(instruction) else pointer+3

            7  -> instr[instr[pointer+3]] = if (parameter1(instruction) < parameter2(instruction)) 1 else 0
            8  -> instr[instr[pointer+3]] = if (parameter1(instruction) == parameter2(instruction)) 1 else 0
        }

        move(opCode)
    }

    private fun parameter1(instruction: String) =
            param(instr[pointer + 1], if (instruction[2] == '0') Mode.POSITION else Mode.IMMEDIATE)

    private fun parameter2(instruction: String) =
            param(instr[pointer + 2], if (instruction[1] == '0') Mode.POSITION else Mode.IMMEDIATE)

    private fun param(parameter: Int, mode: Mode): Int {
        when (mode) {
            Mode.IMMEDIATE -> return parameter
            Mode.POSITION  -> return instr[parameter]
        }
    }

    private fun move(opCode: Int) {
        when (opCode) {
            1 -> pointer += 4
            2 -> pointer += 4
            3 -> pointer += 2
            4 -> pointer += 2

            7 -> pointer += 4
            8 -> pointer += 4
            99 -> pointer += 1
        }
    }
}