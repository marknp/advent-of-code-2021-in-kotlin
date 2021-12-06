package de.marion.aoc2021

import runaoc

private const val DAY = 6
private const val PART_ONE_TEST_RESULT: Long = 5934
private const val PART_TWO_TEST_RESULT: Long = 26984457539

private const val PART_ONE_ACTUAL_RESULT: Long = 361169
private const val PART_TWO_ACTUAL_RESULT: Long = 21406

private fun part1(input: List<String>): Long {
    return solve(input, 80)
}

private fun solve(input: List<String>, upper: Int): Long {
    var fishs = input[0].split(",").map { it.toInt() }
        .groupBy { it }.map { it.key to it.value.size.toLong() }.toMap()
    for (i in 1..upper) {
        fishs =
            fishs.flatMap {
                (if (it.key > 0)
                    mapOf(it.key - 1 to it.value)
                else mapOf(6 to it.value, 8 to it.value)).asIterable()
            }
                .groupBy { it.key }
                .map { it.key to it.value.sumOf { i -> i.value } }
                .toMap()
    }
    return fishs.map { it.value }.sum()
}

private fun part2(input: List<String>): Long {
    return solve(input, 256)
}

fun main() {
    runaoc(
        DAY,
        2021,
        PART_ONE_TEST_RESULT,
        PART_ONE_ACTUAL_RESULT,
        PART_TWO_TEST_RESULT,
        PART_TWO_ACTUAL_RESULT,
        { input -> part1(input) },
        { input -> part2(input) }
    )
}
