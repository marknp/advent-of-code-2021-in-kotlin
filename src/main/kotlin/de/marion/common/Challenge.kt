package de.marion.common

import java.io.File

abstract class Challenge(
    private val name: String? = null,
    filename: String,
) {
    protected val input = File(javaClass.getResource(filename)?.path ?: "").readText()

    abstract fun part1(): Any?
    abstract fun part2(): Any?

    fun printSolutions() {
        println("Problem: $name")
        part1()?.let { "Solution to part1: $it" }.let(::println)
        part2()?.let { "Solution to part2: $it" }.let(::println)
    }
}