package de.marion.aoc2022

import de.marion.common.readInputLines

fun main() {
    fun part1(input: List<String>): Int {
        val sets = input.map { pairs ->
            pairs.split(",")
                .map {
                    val limits = it.split("-")
                    IntRange(limits[0].toInt(), limits[1].toInt()).toSet()
                }
        }.map {
            val intersect = it[0].intersect(it[1])
            intersect.containsAll(it[0]) || intersect.containsAll(it[1])
        }
        return sets.count { it }
    }

    fun part2(input: List<String>): Int {
        val sets = input.map { pairs ->
            pairs.split(",")
                .map {
                    val limits = it.split("-")
                    IntRange(limits[0].toInt(), limits[1].toInt()).toSet()
                }
        }.map {
            it[0].intersect(it[1])
        }
        return sets.count { it.isNotEmpty() }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInputLines("Day04_test", "aoc2022")
    check(part1(testInput) == 2)

    val input = readInputLines("Day04", "aoc2022")
    val part1 = part1(input)
    println("Part 1: $part1")
    check(part1(input) == 821)

    val part2test = part2(testInput)
    check(part2test == 4)
    val part2 = part2(input)
    println("Part 2: $part2")
    check(part2(input) == 2668)
}
