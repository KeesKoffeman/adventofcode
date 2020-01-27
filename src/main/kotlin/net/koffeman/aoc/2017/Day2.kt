package net.koffeman.aoc.`2017`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day2 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) =
        input.map { s ->  (s.split("\\s+".toRegex())
            .map { it.toInt() })
            .let { it.max()!! - it.min()!! } }
        .sum()

    override fun part2(input: List<String>) =
        input.map { s -> (s.split("\\s+".toRegex())
            .map { it.toInt() })
            .let { it.filter { i -> it.filter { b -> b!=i }.any { c -> i%c == 0 || c%i==0 } } }
            .let { it.max()!! / it.min()!! }
        }
        .sum()
}

fun main(args: Array<String>) = Day2().solve()