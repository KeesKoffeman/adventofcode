package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day3 : AdventOfCodePuzzle {

    override fun part1(input: List<String>): Any {
        return input.map { s -> Claim.from(s) }
                .map { claim -> claim.inches() }
                .flatten()
                .groupingBy { pair: Pair<Int, Int> -> pair }
                .eachCount()
                .filter { entry -> entry.value > 1 }
                .count()
    }

    override fun part2(input: List<String>): Any {
        val claims = input.map { s -> Claim.from(s) }
        val inchesClaimedOnce = claims
                .map { claim -> claim.inches() }
                .flatten()
                .groupingBy { pair: Pair<Int, Int> -> pair }
                .eachCount()
                .filter { entry -> entry.value == 1 }
                .map { entry -> entry.key }

        val claimsWithoutOverlap = claims.filter { claim -> inchesClaimedOnce.containsAll(claim.inches()) }
        return claimsWithoutOverlap.first().id
    }

    private data class Claim(val id:Int, val x:Int, val y:Int, val w:Int, val h:Int) {
        companion object {
            fun from(s:String) : Claim {
                val (id,x,y,w,h) = "^#([0-9]+?) @ ([0-9]+?),([0-9]+?): ([0-9]+?)x([0-9]+?)$".toRegex().find(s)!!.destructured
                return Claim(id.toInt(), x.toInt(), y.toInt(),w.toInt(), h.toInt())
            }
        }

        fun inches() : List<Pair<Int,Int>> {
            val horizontalRange = x+1..x+w
            val verticalRange = y+1..y+h
            return horizontalRange.map { i -> verticalRange.map { j -> Pair(i,j) } }.flatten()
        }
    }
}

fun main() = Day3().solve()