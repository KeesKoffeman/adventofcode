package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day15 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = Battlefield(input).play()
    override fun part2(input: List<String>) = ""
}

class Battlefield(val input: List<String>) {

    val tiles : MutableMap<Int,MutableMap<Int,Tile>> = mutableMapOf()
    val elves = mutableSetOf<Elf>()
    val goblins = mutableSetOf<Goblin>()
    var rounds = 0

    init {
        input.forEachIndexed { y, s -> s.forEachIndexed { x, c ->
            when (c) {
                '#' -> tiles.computeIfAbsent(x) { mutableMapOf()}[y] = Tile(x,y,true)
                '.' -> tiles.computeIfAbsent(x) { mutableMapOf()}[y] = Tile(x,y)
                'G' -> { tiles.computeIfAbsent(x) { mutableMapOf()}[y] = Tile(x,y); goblins.add(Goblin(x,y)) }
                'E' -> { tiles.computeIfAbsent(x) { mutableMapOf()}[y] = Tile(x,y); elves.add(Elf(x,y))}
                }
            }
        }
    }

    fun play() : Int {
        while (elves.any { it.alive } && goblins.any{ it.alive } ) {

            var actors = mutableListOf<Actor>()
            actors.addAll(elves.filter { it.alive })
            actors.addAll(goblins.filter { it.alive })

            println("Round $rounds.")
            (0 until tiles[0]!!.size).forEach { y ->
                (0 until tiles.size).forEach { x ->
                    val elf = elves.firstOrNull { it.alive && it.x == x && it.y == y }
                    val goblin = goblins.firstOrNull { it.alive && it.x == x && it.y == y }
                    when {
                        elf != null -> print(elf.char())
                        goblin != null -> print(goblin.char())
                        else -> print(tiles[x]!![y]!!.char())
                    }
                }
                print("   ${actors.filter { it.y == y }.sortedBy { it.x }.map { "${it.char()}(${it.hitPoints})" }.joinToString(", ")}")
                println()
            }
            println()

            actors.sortedWith(compareBy<Actor> { it.x }.thenBy { it.y }).forEach {
                it.moveOrAttack(this)
            }

            rounds ++
        }

        val elfScore = elves.filter { it.alive }.sumBy { it.hitPoints }
        val goblinScore = goblins.filter { it.alive }.sumBy { it.hitPoints }
        println("$rounds * ${Math.max(elfScore, goblinScore)} = ${rounds * (elfScore + goblinScore)}")
        return rounds * (elfScore + goblinScore)
    }

    class Tile(var x:Int, var y:Int, var blocked : Boolean = false) {
        fun location() = Pair(x,y)
        fun adjecent(tiles: Map<Int, Map<Int,Tile>>, actors : List<Actor>) : List<Tile> {

            var nextTiles = mutableListOf<Tile>()

            if (!tiles[x-1]!![y]!!.blocked && actors.none { it.x == x-1 && it.y == y }) { nextTiles.add(tiles[x-1]!![y]!!)}
            if (!tiles[x+1]!![y]!!.blocked && actors.none { it.x == x+1 && it.y == y }) { nextTiles.add(tiles[x+1]!![y]!!)}

            if (!tiles[x]!![y-1]!!.blocked && actors.none { it.x == x   && it.y == y-1 }) { nextTiles.add(tiles[x]!![y-1]!!)}
            if (!tiles[x]!![y+1]!!.blocked && actors.none { it.x == x   && it.y == y+1 }) { nextTiles.add(tiles[x]!![y+1]!!)}

            return nextTiles
        }

        fun char() = if (blocked) '#' else '.'
    }

    class Elf(x : Int,y : Int,attackPower: Int =3 ,hitPoints: Int = 200) : Actor(x,y,attackPower,hitPoints) {
        override fun char() = 'E'
        override fun moveOrAttack(battlefield: Battlefield) = moveOrAttack(battlefield.tiles, battlefield.elves.toList<Actor>() + battlefield.goblins, battlefield.goblins.filter { it.alive })
    }

    class Goblin(x : Int,y : Int,attackPower: Int =3,hitPoints: Int =200) : Actor(x,y,attackPower,hitPoints) {
        override fun char() = 'G'
        override fun moveOrAttack(battlefield: Battlefield) = moveOrAttack(battlefield.tiles, battlefield.elves.toList<Actor>() + battlefield.goblins, battlefield.elves.filter { it.alive })
    }

    abstract class Actor(var x:Int, var y:Int, var attackPower : Int =3, var hitPoints : Int = 200) {

        fun moveOrAttack(tiles : Map<Int,Map<Int,Tile>>, actors : List<Actor>, enemies : List<Actor>) {

            val shortestPathPerEnemy = enemies.map { it to this.shortestPath(it, tiles, actors) }.filter { it.second.isNotEmpty() }
            val shortestPath = shortestPathPerEnemy.sortedBy { it.second.size }.firstOrNull()?.second ?: emptyList()

            when(shortestPath.size) {
                0 -> "" //println("Nothing to do. Awkward")
                1 -> attack(select(shortestPathPerEnemy.filter { it.second.size == shortestPath.size }.map { it.first }))
                else -> {
                    val nextTiles  = shortestPathPerEnemy.filter { it.second.size == shortestPath.size }.flatMap { it.second.first() }
                    val nextTile = selectTile(nextTiles)
                    move(nextTile.location())
                    if (enemies.map { it to this.shortestPath(it, tiles, actors) }.firstOrNull { it.second.size == 1 } != null) {
                        moveOrAttack(tiles, actors, enemies)
                    }
                }
            }
        }

        private fun selectTile(tiles: List<Tile>): Tile {

            val right = tiles.firstOrNull { it.x == this.x+1 && it.y == this.y}
            if (right != null) return right

            val below = tiles.firstOrNull { it.x == this.x && it.y == this.y+y}
            if (below != null) return below

            val above = tiles.firstOrNull { it.x == this.x && it.y == this.y-y}
            if (above != null) return above

            val left = tiles.firstOrNull { it.x == this.x-1 && it.y == this.y}
            if (left != null) return left

            return tiles.first()
        }

        private fun select(actors: List<Actor>): Actor {

            val lowestHitPoint = actors.sortedBy { it.hitPoints }.map { it.hitPoints }.first()

            val right = actors.firstOrNull { it.hitPoints == lowestHitPoint && it.x == this.x+1 && it.y == this.y }
            if (right != null) return right

            val below = actors.firstOrNull { it.hitPoints == lowestHitPoint && it.x == this.x && it.y == this.y+y}
            if (below != null) return below

            val above = actors.firstOrNull { it.hitPoints == lowestHitPoint && it.x == this.x && it.y == this.y-y}
            if (above != null) return above

            val left = actors.firstOrNull { it.hitPoints == lowestHitPoint && it.x == this.x-1 && it.y == this.y}
            if (left != null) return left

            return actors.first()
        }

        abstract fun moveOrAttack(battlefield: Battlefield)

        private fun move(to : Pair<Int,Int>) {
            this.x = to.first
            this.y = to.second
            println("${this.char()} moves to ${this.location()}")
        }

        private fun attack(enemy: Battlefield.Actor) {
            println("${this.char()} at ${this.location()} attacks ${enemy.char()} at ${enemy.location()}!!!")
            enemy.hitPoints -= this.attackPower
            if (enemy.hitPoints < 0) enemy.alive = false
        }

        private fun shortestPath(enemy: Actor, tiles: Map<Int, Map<Int, Tile>>, actors : List<Actor>) : List<List<Tile>> {
            val paths : List<List<Tile>> = allPaths(enemy, tiles, actors)

            if (paths.isNotEmpty()) {
                val shortest =  paths.filter { it.isNotEmpty() }.sortedBy { it.size }.first().size
                return paths.filter { it.size == shortest }
            }
            return emptyList()
        }

        private fun allPaths(enemy: Actor, tiles: Map<Int, Map<Int, Tile>>, actors : List<Actor>): List<List<Tile>> {

            val actorsWithoutEnemy = actors.filter { it != enemy && it != this }.filter { it.alive }

            val paths = tiles[x]!![y]!!.adjecent(tiles, actorsWithoutEnemy).map { mutableListOf(it) }.toMutableList()

            if (paths.size>0) {
                while (paths.any { it.last().location() != enemy.location() }) {
                    paths.filter { it.last().location() != enemy.location() }.forEach { path ->
                        paths.remove(path)
                        path.last().adjecent(tiles, actorsWithoutEnemy).filter { !path.contains(it) }.map { (path + it).toMutableList() }.forEach { paths.add(it) }
                    }
                }
            }
            return paths
        }

        private fun location() = Pair(x,y)
        abstract fun char() : Char

        var alive:Boolean = true

    }
}

fun main(args: Array<String>) = Day15().solve()
