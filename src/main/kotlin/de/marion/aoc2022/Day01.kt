package de.marion.aoc2022

import de.marion.common.readInputLines
import de.marion.common.readInputText

fun main() {
    fun part1(input: List<String>): Int {
        val map = input.first()
            .split("\n\n")
            .map { it.split("\n") }
            .map { l -> l.sumOf { it.toInt() } }
        return map.maxOf { it }


    }

    fun part2(input: List<String>): Int {
        val map = input.first()
            .split("\n\n")
            .map { it.split("\n") }
            .map { l -> l.sumOf { it.toInt() } }
        return map.sorted().reversed().take(3).sumOf { it }

    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInputText("Day01_test", "aoc2022")
    check(part1(testInput) == 24000)

    val input = readInputText("Day01", "aoc2022")
    val part1 = part1(input)
    println("Part 1: $part1")
    check(part1(input) == 69528)

    val part2test = part2(testInput)
    check(part2test == 45000)
    val part2 = part2(input)
    println("Part 2: $part2")
    check(part2(input) == 206152)
}
