package de.marion.aoc2021

import runaoc
import java.util.*

private const val DAY = 10
private const val PART_ONE_TEST_RESULT: Long = 26397
private const val PART_TWO_TEST_RESULT: Long = 288957

private const val PART_ONE_ACTUAL_RESULT: Long = 321237
private const val PART_TWO_ACTUAL_RESULT: Long = 2360030859

private fun part1(input: List<String>): Number {
    val p = input.map { it.toCharArray() }
    val map = p.map { it.getResult(false) }
    return map.sumOf { it.toLong() }
}

private fun CharArray.getResult(includeCompletion: Boolean): Number {
    return getFirstInvalid(this, includeCompletion) ?: 0
}

private fun getFirstInvalid(chars: CharArray, includeCompletion: Boolean): Number? {
    val wrongCharsPunishment = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137
    )

    val map = mapOf('{' to '}', '<' to '>', '{' to '}', '(' to ')', '[' to ']')
    val stack = ArrayDeque<Char>()
    for (char in chars) {
        if (setOf('(', '{', '<', '[').contains(char)) {
            stack.push(char)
        } else {
            val last = stack.pop()
            if (map[last] != char) {
                return if (!includeCompletion) wrongCharsPunishment[char] else null
            }
        }
    }
    return if (!includeCompletion) {
        null
    } else {
        val completionPoints = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)
        val completionChars = stack.toList().map { map[it] }
        var sum: Long = 0
        for (char in completionChars) {
            sum *= 5
            sum += completionPoints[char] ?: 0
        }
        sum
    }

}

private fun part2(input: List<String>): Long {
    val p = input.map { it.toCharArray() }
    val map = p.map { it.getResult(true) }.filter { it != 0 }.map { it.toLong() }
    return map.sorted()[(map.size - 1) / 2]
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
