package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle
import java.util.*

class Day9 : AdventOfCodePuzzle {

    override fun part1(input: List<String>): Any = StupidElfGame.parse(input).map { it.highscore() }

    override fun part2(input: List<String>): Any = StupidElfGame.parse(input, 100).map { it.highscore() }

    class StupidElfGame(private val players:Int, private val marbles:Int, private val multiplier : Int = 1) {

        fun highscore(): Any = play().maxBy { it.value }!!.value

        private fun play(): MutableMap<Int, Long> {

            val circle = Circle()
            val scores: MutableMap<Int, Long> = (0..players).map { it to 0L }.toMap().toMutableMap()

            (1..marbles * multiplier).forEach { marble ->
                if (marble % 23 == 0) {
                    val player = marble % players
                    scores[player] = scores[player]!! + (marble + circle.remove())
                } else {
                    circle.add(marble)
                }
            }

            return scores
        }

        class Circle(private val deque: ArrayDeque<Int> = ArrayDeque(mutableListOf(0))) {

            fun add(marble : Int) {
                repeat(2) { deque.addFirst(deque.removeLast()) }
                deque.addLast(marble)
            }

            fun remove() : Int {
                repeat(7) { deque.addLast(deque.removeFirst()) }
                return deque.removeLast()
            }
        }

        companion object {
            fun parse(input: List<String>, multiplier:Int = 1) : List<StupidElfGame> =
                    input.map { "([0-9]+) players; last marble is worth ([0-9]+) points".toRegex().find(it)!!.destructured.let { (a, b) -> StupidElfGame(a.toInt(), b.toInt(), multiplier) } }
        }
    }
}

fun main(args: Array<String>) = Day9().solve()