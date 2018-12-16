package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle
class Day16 : AdventOfCodePuzzle {

    override fun part1(input: List<String>):Any = Sampler.from(input).findNumberSamplesWithMoreThanThreePossibleOperations()
    override fun part2(input: List<String>) = Program(input.subList(3006, input.size), Sampler.from(input).dictionary()).compute()
}

class Program(val input : List<String>, private val dictionary : Map<Int, Operations>) {

    private val instructions : List<Instruction>
    private var register = listOf(0,0,0,0)

    init { instructions = input.map { Sampler.parse(it) }.map { Instruction(dictionary[it[0]]!!, it[1], it[2], it[3]) } }

    fun compute(): Int {
        instructions.forEach {
            register = it.compute(register)
        }
        return register[0]
    }

    class Instruction(private val operation : Operations, private val inputA:Int, private val inputB:Int, private val output:Int) {

        fun compute(register: List<Int>): List<Int> {

            var computed = register.toMutableList()

            val rA = register[inputA]
            val rB = register[inputB]
            val vA = inputA
            val vB = inputB

            computed[output] = when (operation) {
                Operations.ADDR -> rA + rB
                Operations.ADDI -> rA + vB
                Operations.MULR -> rA * rB
                Operations.MULI -> rA * vB
                Operations.BANR -> rA and rB
                Operations.BANI -> rA and vB
                Operations.BORR -> rA or rB
                Operations.BORI -> rA or vB
                Operations.SETR -> rA
                Operations.SETI -> vA
                Operations.GTIR -> if (vA>rB) 1 else 0
                Operations.GTRI -> if (rA>vB) 1 else 0
                Operations.GTRR -> if (rA>rB) 1 else 0
                Operations.EQIR -> if (vA == rB) 1 else 0
                Operations.EQRI -> if (rA == vB) 1 else 0
                Operations.EQRR -> if (rA == rB) 1 else 0
            }

            return computed
        }
    }
}

class Sampler(private val samples : List<Sample>) {

    fun dictionary() : Map<Int, Operations> {
        val codeToOperationMap = samples.map { it.opsCode[0] to it.possibleOpsCodes() }.groupBy { it.first }.map { it.key to it.value.map { it.second }.reduce { a,b -> a.intersect(b)}.toMutableSet() }.toMap().toMutableMap()
        while (codeToOperationMap.values.any { it.size > 1 }) {
            val singles = codeToOperationMap.values.filter { it.size == 1 }.map { it.first() }.toSet()
            codeToOperationMap.filter { it.value.size > 1 }.forEach { it ->
                codeToOperationMap[it.key] = it.value.filter { !singles.contains(it) }.toMutableSet()
            }
        }
        return codeToOperationMap.mapValues { entry -> entry.value.first() }.toSortedMap()
    }

    fun findNumberSamplesWithMoreThanThreePossibleOperations() = samples.map { it to it.possibleOpsCodes() }.filter { it.second.size>2 }.size

    companion object {
        fun from(input : List<String>) = Sampler((if (input.size > 3005 ) input.subList(0,3005) else input ).windowed(4,4).map { Sample(Sampler.parse(it[0]),Sampler.parse(it[2]),Sampler.parse(it[1])) })
        fun parse(input : String) : List<Int> = "([0-9]{1,2})".toRegex().findAll(input).toList().map { it.value.toInt() }
    }
}

class Sample(private val before : List<Int>, private val after : List<Int>, val opsCode : List<Int> ) {
    fun possibleOpsCodes() : Set<Operations> = Operations.values().filter { Program.Instruction(it, opsCode[1], opsCode[2], opsCode[3]).compute(before) == this.after }.toSet()
}

enum class Operations { ADDR, ADDI, MULR, MULI, BANR, BANI, BORR, BORI, SETR, SETI, GTIR, GTRI, GTRR, EQIR, EQRI, EQRR }

fun main(args: Array<String>) = Day16().solve()