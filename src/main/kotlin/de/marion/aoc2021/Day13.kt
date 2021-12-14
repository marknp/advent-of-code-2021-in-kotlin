package de.marion.aoc2021

import readInputText
import runaoc

private const val DAY = 13
private const val PART_ONE_TEST_RESULT: Int = 17
private const val PART_TWO_TEST_RESULT: Int = 2

private const val PART_ONE_ACTUAL_RESULT: Int = 763
private const val PART_TWO_ACTUAL_RESULT: Int = 476

private fun part1(input: List<String>): Number {
    val (valuesParsed, instructionsParsed) = parse(input)
    return solv(valuesParsed, instructionsParsed, true)
}

private fun solv(
    valuesParsed: List<Pair<Int, Int>>, instructionsParsed: List<Pair<C, Int>>, breakAfterOneRound: Boolean
): Int {
    var matrix =
        Array(valuesParsed.maxOf { it.second } + 1) { BooleanArray(valuesParsed.maxOf { it.first } + 1) { false } }
    for (value in valuesParsed) {
        matrix[value.second][value.first] = true
    }
    for ((c, v) in instructionsParsed) {
        if (c == C.Y) {
            val result = matrix.sliceArray(0 until v)
            val upper = matrix.sliceArray(0 until v)
            val reversed = matrix.sliceArray(v + 1 until matrix.size)
            for (i in reversed.indices) {
                result[upper.size - 1 - i] =
                    upper[upper.size - 1 - i].zip(reversed[i]).map { it.first || it.second }.toBooleanArray()
            }
            println("after fold")
            matrix = result

        } else {
            val newMatrix = matrix.map {
                it.sliceArray(0 until v).zip(it.slice(v + 1 until it.size).reversed()).map { z -> z.first || z.second }
                    .toBooleanArray()
            }.toTypedArray()
            matrix = newMatrix
        }
        if (breakAfterOneRound) return matrix.sumOf { it.count { i -> i } }
    }
    matrix.forEach { line -> println(line.joinToString("") { if (it) "#" else "." }) }
    return 2

    /*
###..#..#..##..#....###...##..###...##..
#..#.#..#.#..#.#....#..#.#..#.#..#.#..#.
#..#.####.#..#.#....#..#.#....#..#.#..#.
###..#..#.####.#....###..#....###..####.
#.#..#..#.#..#.#....#.#..#..#.#.#..#..#.
#..#.#..#.#..#.####.#..#..##..#..#.#..#.
     */
}

private fun Char.toC(): C {
    return if (this == 'x') {
        C.X
    } else C.Y
}

enum class C {
    X, Y

}

private fun part2(input: List<String>): Number {
    val (valuesParsed, instructionsParsed) = parse(input)
    return solv(valuesParsed, instructionsParsed, false)
}

private fun parse(input: List<String>): Pair<List<Pair<Int, Int>>, List<Pair<C, Int>>> {
    val (values, instructions) = input[0].split("\n\n").map { it.split("\n") }
    val valuesParsed = values.map { it.split(",") }.map { it[0].toInt() to it[1].toInt() }
    val instructionsParsed = instructions.map { it.split("=") }.map { it[0].last().toC() to it[1].toInt() }
    return Pair(valuesParsed, instructionsParsed)
}


fun main() {
    runaoc(DAY,
        2021,
        PART_ONE_TEST_RESULT,
        PART_ONE_ACTUAL_RESULT,
        PART_TWO_TEST_RESULT,
        PART_TWO_ACTUAL_RESULT,
        { input -> part1(input) },
        { input -> part2(input) },
        read = { a, b -> readInputText(a, b) })
}
