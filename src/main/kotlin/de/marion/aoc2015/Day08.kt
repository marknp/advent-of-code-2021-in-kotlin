package de.marion.aoc2015

import runaoc

private const val DAY = 8
private const val PART_ONE_TEST_RESULT = 15
private const val PART_TWO_TEST_RESULT = 25

private const val PART_ONE_ACTUAL_RESULT = 1371
private const val PART_TWO_ACTUAL_RESULT = 3783758

private fun part1(input: List<String>): Int {
    val codeLength = input.map { it.codeLength() }
    val memoryLength = input.map { it.memoryLength() }
    return codeLength.sumOf { it } - memoryLength.sumOf { it }
}

private fun part2(input: List<String>): Int {
    val one = input.map { it.length }
    val two = input.map { it.escape() }.map { it.length }
    return two.sumOf { it } - one.sumOf { it }
}

private fun String.escape(): String {
    val s = "\"" +
            this.replace("""\""", """\\""")
                .replace(""""""", """\"""")
                .replace("""\\x..""".toRegex(), """\\xxx""") +
            "\""
    return s
}

private fun String.memoryLength(): Int {
    val a = this.removePrefix("\"")
        .replace("""\\\\""".toRegex(), "x")
        .removeSuffix("\"")
        .replace("""\\x..""".toRegex(), "x")
        .replace("""\\.""".toRegex(), "x")


    return a.length
}

private fun String.codeLength(): Int {
    return this.length
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