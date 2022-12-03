package de.marion.aoc2022

import de.marion.common.readInputLines

fun main() {
    fun part1(input: List<String>): Int {
        val sets = input.map { it.subSequence(0, it.length / 2) to it.subSequence(it.length / 2, it.length) }
        val duplicates = sets.map { it.first.toSet().intersect(it.second.toSet()) }
        return duplicates.sumOf { it.first().toValue() }


    }

    fun part2(input: List<String>): Int {
        val groups = input.chunked(3)
        val duplicates = groups.map { it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet()) }
        return duplicates.sumOf { it.first().toValue() }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInputLines("Day03_test", "aoc2022")
    check(part1(testInput) == 157)

    val input = readInputLines("Day03", "aoc2022")
    val part1 = part1(input)
    println("Part 1: $part1")
    check(part1(input) == 8139)

    val part2test = part2(testInput)
    check(part2test == 70)
    val part2 = part2(input)
    println("Part 2: $part2")
    check(part2(input) == 2668)
}

private fun Char.toValue(): Int {
    val values = ('a'..'z').toList() + ('A'..'Z').toList()
    return values.indexOf(this) + 1
}
