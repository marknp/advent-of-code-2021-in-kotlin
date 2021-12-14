package de.marion.aoc2021

import runaoc
import kotlin.math.roundToLong

private const val DAY = 14
private const val PART_ONE_TEST_RESULT: Long = 1588
private const val PART_TWO_TEST_RESULT: Long = 2188189693529

private const val PART_ONE_ACTUAL_RESULT: Long = 3411
private const val PART_TWO_ACTUAL_RESULT: Long = 7477815755570

private fun part1(input: List<String>): Number {
    return solve(input, 10)
}

private fun solve(input: List<String>, size: Int): Long {
    val template = input[0]
    val instructions =
        input.subList(2, input.size).map { it.split(" -> ") }
            .map { it[0] to it[1].toCharArray()[0] }.toMap()
    val result = two(template, instructions, size)
    return result
}


private fun two(template: String, instructions: Map<String, Char>, nRounds: Int): Long {
    var vals = instructions.map { it.key to 0L }.toMap().toMutableMap()
    template.windowed(2)
        .forEach {
            vals[it] = vals[it]!! + 1
        }
    for (r in 1..nRounds) {
        println("round" + r + ": " + vals.map { it.value }.sum())
        val newVals = instructions.map { it.key to 0L }.toMap().toMutableMap()
        vals.forEach {
            if (it.value != 0L) {
                val first = (it.key[0].toString() + instructions[it.key])
                newVals[first] = newVals[first]!! + it.value
                val second = (instructions[it.key].toString() + it.key[1].toString())
                newVals[second] = newVals[second]!! + it.value
            }
        }
        vals = newVals

    }
    val groupBy = vals.flatMap { listOf(it.key[0] to it.value, it.key[1] to it.value) }
        .groupBy { it.first }
        .map { it.key to (it.value.sumOf { s -> s.second }) }
    val toMap = groupBy.toMap()
    val d = (toMap.maxOf { it.value } - toMap.minOf { it.value }) / 2.0
    return d.roundToLong()
}

private fun one(
    template: String,
    size: Int,
    instructions: Map<String, String>
): Long {
    var result = template
    for (i in 1..size) {
        println("round" + i + ": " + result.length)
        val windowed = result.windowed(2)
        val map = windowed
            .mapIndexed { index, it -> instructions.mapit(index, it) }
        result = map.joinToString("")
    }
    return result.mostCommon() - result.leastCommon()
}

private fun Map<String, String>.mapit(index: Int, it: String): String {
    val toInsert = this[it]
    if (index == 0) {
        return it[0].toString() + (toInsert ?: "") + it[1].toString()
    }
    return if (toInsert != null) toInsert + it[1] else it[1].toString()


}

private fun String.mostCommon(): Long {
    return this.toCharArray().groupBy { it }.maxOf { it.value.size.toLong() }
}

private fun String.leastCommon(): Long {
    return this.toCharArray().groupBy { it }.minOf { it.value.size.toLong() }
}

private fun part2(input: List<String>): Number {
    return solve(input, 40)
}

fun main() {
    runaoc(DAY,
        2021,
        PART_ONE_TEST_RESULT,
        PART_ONE_ACTUAL_RESULT,
        PART_TWO_TEST_RESULT,
        PART_TWO_ACTUAL_RESULT,
        { input -> part1(input) },
        { input -> part2(input) })
}
