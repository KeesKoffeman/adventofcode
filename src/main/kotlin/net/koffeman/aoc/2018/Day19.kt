package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle
import java.lang.Thread.sleep

class Day19 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = ProgramWithInstructionPointers.from(input.first()[4].toString().toInt(), input.subList(1, input.size)).compute()

    override fun part2(input: List<String>) =
            ProgramWithInstructionPointers.from(input.first()[4].toString().toInt(), input.subList(1, input.size)).sumFactorsOfN(10551376)
}

class ProgramWithInstructionPointers(val ipIndex: Int, instructions: List<Program.Instruction>) : Program(instructions) {

    private var executions = 0
    private var ip = 0
    private var registers = mutableListOf(0,0,0,0,0,0)

    // TODO: autodetect n
    fun sumFactorsOfN(n: Int)= (1 .. n).filter { n % it == 0 }.sum()

    fun compute(register0 : Int = 0) : Int {
        registers[0] = register0
        return compute()
    }

    override fun compute(): Int {

        var instructionsExecuted = mutableSetOf<Instruction>()

        var debug = false
        ip = registers[ipIndex]

        while (ip<instructions.size) {

            registers[ipIndex] = ip

            val instruction = instructions[ip]
            val result = instruction.compute(registers).toMutableList()

            if (debug) {
                println("${executions.toString().padStart(5, ' ')} ${ip.toString().padStart(2, ' ')} $registers $instruction $result")
                sleep(300)
            }

            registers = result.toMutableList()
            ip = registers[ipIndex]+1

            executions++
            instructionsExecuted.add(instruction)

            if (executions % 1000 == 0) {
                val reg1 = registers[1]
//                if (reg1 < 1)
                    println(reg1)
//                if (reg1 >= Int.MAX_VALUE) println(reg1)
            }

        }

        println("Operations not executed = ${instructions.toSet() - instructionsExecuted}")

        return registers[0]
    }

    companion object {
        fun from(ip: Int, input: List<String>) = ProgramWithInstructionPointers(ip, input.map { s -> "([a-z]{4}) ([0-9]+) ([0-9]+) ([0-9]+)".toRegex().find(s)!!.destructured.let { (instr, a, b, c) -> Instruction(Operations.valueOf(instr.toUpperCase()), a.toInt(), b.toInt(), c.toInt()) } })
    }
}


fun main(args: Array<String>) = Day19().solve()