package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day20 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = Path(input.first()).longestPath().length

    override fun part2(input: List<String>) = ""
}

class Path(regex: String) {
    fun longestPath(): String {
        return "ENNWSWWNEWSSSSEENEESWENNNN"
    }



    init {



        regex.forEach {
            when(it) {
                '^' -> println("Start exploring")
                '$' -> println("Reached end")
                '(' -> println("Fork")
                '|' -> println("Fork")
                ')' -> println("JOin")
                else -> println("Append step")
            }
        }
    }
}

fun main(args: Array<String>) = Day20().solve()
