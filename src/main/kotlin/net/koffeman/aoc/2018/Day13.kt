package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day13 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = Track.from(input).detectFirstCrash().let { "${it.x},${it.y}" }

    override fun part2(input: List<String>) = Track.from(input).positionOfLastCart()
}
class Track(val rails : Map<Int,Map<Int,Rails>>, var vehicles : MutableList<Vehicle>) {

    fun detectFirstCrash(): Vehicle {

        var crash: Vehicle?

        do {
            tick()
            crash = vehicles.firstOrNull { it.crashed == true}
        } while (crash == null)

        return crash
    }

    fun positionOfLastCart(): Pair<Int,Int> {

        do {
            tick()
            vehicles = vehicles.groupingBy { it.location() }.eachCount().filter { it.value == 1 }.map { p -> vehicles.filter { v -> v.location() == p.key} }.flatten().toMutableList()
        } while (vehicles.filter { !it.crashed }.size>1)

        return vehicles.first().let { it.location() }
    }

    private fun tick() {
        vehicles.sortedWith(compareBy<Vehicle>{it.y}.thenBy{it.x}).forEach {
            if (!it.crashed) {
                it.move(rails)
                vehicles.groupBy { it.location() }.filter { it.value.size>1 }.map { it.value }.flatten().forEach { it.crashed = true }
            }
        }
    }

    private fun print() {
        println("*********")
        (0 until rails[0]!!.size).forEach { y ->
            rails.keys.forEach { x ->

                val vehicle = vehicles.firstOrNull { it.x == x && it.y == y }
                if (vehicle == null) {
                    print(rails[x]!![y]?.c ?: ' ')
                } else {
                    print(vehicle.c.c)
                }
            }
            println()
        }
    }

    companion object {
        fun from(input: List<String>) : Track {

            val rails : MutableMap<Int,MutableMap<Int,Rails>> = mutableMapOf()
            val vehicles : MutableList<Vehicle> = mutableListOf()

            input.forEachIndexed { y, s ->
                s.forEachIndexed { x, c ->
                var rail = Rails.values().firstOrNull { it.c == c}
                if (rail == null) {
                    val vehicle = Vehicles.values().first { it.c == c }.let { Vehicle(x,y,it) }
                    vehicles.add(vehicle)
                    rail = if (vehicle.c.isNorthOrSoundBound()) Rails.VERTICAL else Rails.HORIZONTAL
                }
                rails.computeIfAbsent(x) { mutableMapOf() }[y] = rail }
            }

            return Track(rails, vehicles)
        }
    }
}


class Vehicle(var x:Int, var y:Int, var c:Vehicles) {

    var turns = 0
    var crashed: Boolean = false

    fun location() = Pair(x,y)

    fun move(rails: Map<Int, Map<Int, Any>>) {

        when (c) {
            Vehicles.NORTH_BOUND -> y--
            Vehicles.SOUTH_BOUND -> y++
            Vehicles.WEST_BOUND -> x--
            Vehicles.EAST_BOUND -> x++
        }

        val next = rails[x]!![y]!!

        when (next) {
            Rails.CROSSING -> turn()
            Rails.DOWN -> { c = when(this.c) { // \
                                Vehicles.NORTH_BOUND -> Vehicles.WEST_BOUND
                                Vehicles.SOUTH_BOUND -> Vehicles.EAST_BOUND
                                Vehicles.EAST_BOUND  -> Vehicles.SOUTH_BOUND
                                Vehicles.WEST_BOUND  -> Vehicles.NORTH_BOUND
                }
            }
            Rails.UP -> { c = when(this.c) { // /
                                Vehicles.NORTH_BOUND -> Vehicles.EAST_BOUND
                                Vehicles.SOUTH_BOUND -> Vehicles.WEST_BOUND
                                Vehicles.EAST_BOUND  -> Vehicles.NORTH_BOUND
                                Vehicles.WEST_BOUND  -> Vehicles.SOUTH_BOUND
                }
            }
        }
    }

    private fun turn() {
        when(Turns.values()[turns%Turns.values().size]) {
            Turns.LEFT -> c = c.turnLeft()
            Turns.RIGHT -> c = c.turnRight()
        }
        turns ++
    }
}

enum class Turns {
    LEFT, STRAIGHT, RIGHT
}

enum class Rails(val c:Char) {
    CROSSING('+'),
    HORIZONTAL('-'),
    VERTICAL('|'),
    DOWN('\\'),
    UP('/'),
    OFFROAD(' ')
}

enum class Vehicles(val c:Char) {
    NORTH_BOUND('^'),
    SOUTH_BOUND('v'),
    WEST_BOUND('<'),
    EAST_BOUND('>');

    fun isNorthOrSoundBound() : Boolean = this == NORTH_BOUND || this == SOUTH_BOUND

    fun turnLeft() : Vehicles {
        return when(this) {
            NORTH_BOUND -> WEST_BOUND
            SOUTH_BOUND -> EAST_BOUND
            EAST_BOUND -> NORTH_BOUND
            WEST_BOUND -> SOUTH_BOUND
        }
    }

    fun turnRight() : Vehicles {
        return when(this) {
            NORTH_BOUND -> EAST_BOUND
            SOUTH_BOUND -> WEST_BOUND
            EAST_BOUND -> SOUTH_BOUND
            WEST_BOUND -> NORTH_BOUND
        }
    }
}

fun main(args: Array<String>) = Day13().solve()