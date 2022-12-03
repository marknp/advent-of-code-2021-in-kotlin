package de.marion.aoc2015

import de.marion.common.runaoc

private const val DAY = 5
private const val PART_ONE_TEST_RESULT = 3
private const val PART_TWO_TEST_RESULT = 2

private const val PART_ONE_ACTUAL_RESULT = 255
private const val PART_TWO_ACTUAL_RESULT = -1

private fun part1(input: List<String>): Int {
    return input.count { it.isNice() }
}

private fun String.isNice2(): Boolean {
    val indexes = this.windowed(2)
        .mapIndexed { index, s -> Pair(index, s) }
        .groupBy { it.second }
        .values
        .map { a -> a.map { it.first } }
        .filter { it.size > 1 }
        .map { it.size > 2 || it.get(1) - it.get(0) > 1 }

    val hasTwoWithSpace = this.windowed(3).any { it.get(0).equals(it.get(2)) }
    val one = indexes.any{it}
    return one && hasTwoWithSpace
}

private fun String.isNice(): Boolean {
    val one = ".*[aeiou].*[aeiou].*[aeiou].*".toRegex().matches(this)
    val three = !".*(ab|cd|pq|xy).*".toRegex().matches(this)
    val hasDoubleLetter = this.windowed(2).any { it.get(0).equals(it.get(1)) }
    return one && three && hasDoubleLetter
}

private fun part2(input: List<String>): Int {
    return input.count { it.isNice2() }
}


fun main() {
    check("qjhvhtzxzqqjkmpb".isNice2())
    check("xxyxx".isNice2())
    check("aaaa".isNice2())
    check(!"xxxyx".isNice2())
    check(!"uurcxstgmygtbstg".isNice2())
    check(!"ieodomkazucvgmuy".isNice2())
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