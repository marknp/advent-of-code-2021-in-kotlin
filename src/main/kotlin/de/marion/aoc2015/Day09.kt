package de.marion.aoc2015

import de.marion.common.runaoc

private const val DAY = 9
private const val PART_ONE_TEST_RESULT = 605
private const val PART_TWO_TEST_RESULT = 982

private const val PART_ONE_ACTUAL_RESULT = 207
private const val PART_TWO_ACTUAL_RESULT = 804

private fun part1(input: List<String>): Int {
    val parse = prse(input)
    val cities = parse.keys.flatMap { listOf(it.first, it.second) }.toSet()
    var lowest = Int.MAX_VALUE
    for (i in 0..9999999) {
        val shuffled = cities.shuffled()
        val na = shuffled.windowed(2)
            .map { if ((parse[Pair(it[0], it[1])] != null)) parse[Pair(it[0], it[1])] else parse[Pair(it[1], it[0])] }
        val n = na.filterNotNull().sum()
        if (n < lowest) {
            lowest = n
        }
    }
    return lowest
}

private fun prse(input: List<String>): Map<Pair<String, String>, Int> {
    return input.mapNotNull { """(\w*) to (\w*) = (\d*)""".toRegex().matchEntire(it)?.groupValues }
        .associate { it[1] to it[2] to it[3].toInt() }

}

private fun part2(input: List<String>): Int {
    val parse = prse(input)
    val cities = parse.keys.flatMap { listOf(it.first, it.second) }.toSet()
    var biggest = Int.MIN_VALUE
    for (i in 0..9999999) {
        val shuffled = cities.shuffled()
        val na = shuffled.windowed(2)
            .map { if ((parse[Pair(it[0], it[1])] != null)) parse[Pair(it[0], it[1])] else parse[Pair(it[1], it[0])] }
        val n = na.filterNotNull().sum()
        if (n > biggest) {
            biggest = n
        }
    }
    return biggest
}

fun main() {
    runaoc(DAY,
        2015,
        PART_ONE_TEST_RESULT,
        PART_ONE_ACTUAL_RESULT,
        PART_TWO_TEST_RESULT,
        PART_TWO_ACTUAL_RESULT,
        { input -> part1(input) },
        { input -> part2(input) })
}