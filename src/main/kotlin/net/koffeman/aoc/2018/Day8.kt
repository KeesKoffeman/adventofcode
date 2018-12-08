package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle
import java.util.*

class Day8 : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = input.first().license().checksum()

    override fun part2(input: List<String>) = input.first().license().value()

    fun String.license():Node {
        return split(" ").map { it.toInt() }.nodes()
    }

    fun List<Int>.nodes():Node {

        val mutable = this.toMutableList()
        val s : Stack<Node> = Stack()

        val childCount = mutable.removeAt(0)
        val metaCount = mutable.removeAt(0)
        val root = Node(childCount, metaCount, mutableListOf(), mutableListOf())

        var current = root
        s.add(current)

        while (s.isNotEmpty() && mutable.isNotEmpty()) {

            current = s.peek()

            if (current.children.size == current.childCount) {
                (0 until current.metaCount).forEach { _ -> current.metadata.add(mutable.removeAt(0)) }
                s.pop()
            } else {
                val child = Node(mutable.removeAt(0), mutable.removeAt(0), mutableListOf(), mutableListOf())
                current.children.add(child)
                current = child
                s.push(current)
            }
        }

        return root
    }

    class Node(val childCount : Int, val metaCount : Int, val children:MutableList<Node>, val metadata : MutableList<Int>) {
        fun value() : Int = if (this.children.isEmpty()) metadata.sum() else metadata.filter { it > 0 }.map { it - 1 }.filter { it < children.size }.map { children[it] }.sumBy { it.value() }
        fun checksum() : Int = children.sumBy { it.checksum() } + metadata.sum()
        override fun toString(): String = "${children.size} ${metadata.size} ${children.map{ it.toString() }.joinToString(" ")} ${metadata.joinToString(" ")}".replace("  "," ")
    }

}

fun main(args: Array<String>) = Day8().solve()