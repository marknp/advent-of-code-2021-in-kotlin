package de.marion.aoc2015

import runaoc

private const val DAY = 2
private const val PART_ONE_TEST_RESULT = 101
private const val PART_TWO_TEST_RESULT = 48

private const val PART_ONE_ACTUAL_RESULT = 1588178
private const val PART_TWO_ACTUAL_RESULT = 3783758

private fun part1(input: List<String>): Int {
    return input.map { """(\d*)x(\d*)x(\d*)""".toRegex().matchEntire(it) }
        .mapNotNull { it?.groupValues }
        .map { listOf(it[1], it[2], it[3]).map { i -> i.toInt() }.sorted() }
        .sumOf { it[0] * it[1] * 3 + it[1] * it[2] * 2 + it[2] * it[0] * 2 }
}

private fun part2(input: List<String>): Int {
    return input.map { """(\d*)x(\d*)x(\d*)""".toRegex().matchEntire(it) }
        .mapNotNull { it?.groupValues }
        .map { listOf(it[1], it[2], it[3]).map { i -> i.toInt() }.sorted() }
        .sumOf { it[0] * 2 + it[1] * 2 + it[0] * it[1] * it[2] }
}


fun main() {
    runaoc(
        DAY,
        2015,
        PART_ONE_TEST_RESULT,
        PART_ONE_ACTUAL_RESULT,
        PART_TWO_TEST_RESULT,
        PART_TWO_ACTUAL_RESULT,
        { input -> part1(input) },
        { input -> part2(input) }
    )
}