package net.koffeman.aoc.`2018`

import net.koffeman.aoc.AdventOfCodePuzzle

class Day7(private val noOfWorkers:Int = 5, private val delay:Int = 60) : AdventOfCodePuzzle {

    override fun part1(input: List<String>) = Instructions.parse(input).ordered().stringify()

    override fun part2(input: List<String>) = Instructions.parse(input).timed(noOfWorkers, delay)


    data class Instructions(val steps:List<Step>) {

        fun ordered() : Instructions {
            val ordered = mutableListOf<Step>()
            while (ordered.size < steps.size) {
                ordered.add(steps
                    .filter { step -> !ordered.contains(step) }
                    .filter { step -> ordered.map { other -> other.id }.toSet().any { c -> step.succeeds.contains(c) } || step.succeeds.isEmpty() }
                    .filter { step -> if (ordered.isNotEmpty()) ordered.map { other -> other.id }.containsAll(step.succeeds) else true }
                    .sortedBy { step -> step.id }
                    .first()
                )
            }
            return Instructions(ordered)
        }

        fun stringify() = steps.map { step -> step.id }.joinToString("")

        fun timed(noOfWorkers: Int, delay: Int): Int {
            var workload = ordered().steps
            var done = mutableSetOf<Step>()

            var seconds = 0
            val workers = (1..noOfWorkers).map { Worker(delay) }

            while (workload.isNotEmpty() || workers.any { worker -> !worker.isIdle(seconds) }) {
                workers.forEach { worker ->
                    done.addAll(worker.finishedSteps(seconds))
                    if (worker.isIdle(seconds)) {
                        val nextTask = workload.filter { next -> done.map { step -> step.id }.containsAll(next.succeeds) || next.succeeds.isEmpty() }.firstOrNull()
                        if (nextTask != null) {
                            worker.assign(nextTask, seconds)
                            workload = workload.filter { step -> step!=nextTask }
                        }
                    }
                }
                seconds++
            }

            return seconds
        }

        companion object {
            fun parse(input: List<String>) : Instructions = input
                .map { s ->  "^Step ([A-Z]) must be finished before step ([A-Z]) can begin.$".toRegex().find(s)!!.destructured.let { (a,b) -> b.first() to a.first() }}
                .groupBy { pair -> pair.first}
                .map { entry -> entry.key to entry.value.map { pair -> pair.second } }
                .map { (k,v) -> Step(k,v)}
                .let { 
                    list ->
                    val missing = list.map { step -> step.succeeds }.flatten().distinct().filter { step -> !list.map { other -> other.id}.contains(step) }.map { c -> Step(c, emptyList()) }
                    Instructions(list + missing)
                }
        }
    }

    data class Step(val id:Char, val succeeds:List<Char>) {
        fun duration() : Int = id.toInt()-'A'.toInt()+1
    }

    class Worker(var delay:Int, var assigned : MutableMap<Int,Step> = mutableMapOf()) {
        fun isIdle(elapsed: Int): Boolean = if (assigned.keys.isEmpty()) { true } else { assigned.maxBy { entry -> entry.key }.let { entry -> duration(entry!!) <= elapsed } }
        fun assign(step: Step, start: Int) = assigned.put(start, step)
        fun finishedSteps(elapsed:Int): Collection<Day7.Step> = assigned.filter { entry -> duration(entry) <= elapsed }.values
        private fun duration(task: Map.Entry<Int, Step>) = task.key + task.value.duration() + delay
    }
}

fun main() = Day7().solve()