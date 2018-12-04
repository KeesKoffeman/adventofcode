package net.koffeman.aoc.extensions

fun <T> List<T>.repeat(): Sequence<T> = generateSequence(0) { (it + 1) % this.size }.map(::get)