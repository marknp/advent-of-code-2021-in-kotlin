package de.marion.aoc2021

import runaoc

private const val DAY = 11
private const val PART_ONE_TEST_RESULT: Int = 1656
private const val PART_TWO_TEST_RESULT: Int = 195

private const val PART_ONE_ACTUAL_RESULT: Int = 1717
private const val PART_TWO_ACTUAL_RESULT: Int = 476

private fun part1(input: List<String>): Number {
    val size = input.size
    val grid = input.map { it.toCharArray().map { c -> Octopus(c.toString().toInt()) } }
    var nFlash = 0
    for (step in 1..100) {
        val flashed = grid.map { row -> row.map { it.flash() } }
            .mapIndexed { i, booleans -> booleans.mapIndexed { ii, b -> if (b) i to ii else null } }
            .flatten().filterNotNull()

        val flash = flash(flashed, size, grid)
        nFlash += flash
        grid.map { row -> row.map { it.reset() } }
    }
    return nFlash
}

private fun flash(
    flashed: List<Pair<Int, Int>>,
    size: Int,
    grid: List<List<Octopus>>
): Int {
    if (flashed.isEmpty()) {
        return 0
    }
    val neighbors: MutableList<Pair<Int, Int>> = mutableListOf()
    var flashs = 0
    for ((i, j) in flashed) {
        flashs += 1
        neighbors.addAll((i to j).getNeighbours(size))
    }
    val f = neighbors.map { if (grid[it.first][it.second].flash()) it else null }.filterNotNull()
    return flashs + flash(f, size, grid)
}

class Octopus(var intensity: Int) {
    fun flash(): Boolean {
        intensity++
        return intensity == 10

    }

    override fun toString(): String {
        return intensity.toString()
    }

    fun reset() {
        if (intensity >= 10) {
            intensity = 0
        }
    }
}


private fun Pair<Int, Int>.getNeighbours(size: Int): List<Pair<Int, Int>> {
    return listOf(
        first - 1 to second,
        first + 1 to second,
        first to second - 1,
        first to second + 1,
        first - 1 to second + 1,
        first + 1 to second - 1,
        first - 1 to second - 1,
        first + 1 to second + 1
    )
        .filter { it.first >= 0 }
        .filter { it.second >= 0 }
        .filter { it.first < size }
        .filter { it.second < size }
}


private fun part2(input: List<String>): Number {
    val size = input.size
    val grid = input.map { it.toCharArray().map { c -> Octopus(c.toString().toInt()) } }
    var nFlash = 0
    for (step in 1..100000) {
        val flashed = grid.map { row -> row.map { it.flash() } }
            .mapIndexed { i, booleans -> booleans.mapIndexed { ii, b -> if (b) i to ii else null } }
            .flatten().filterNotNull()

        val flash = flash(flashed, size, grid)
        nFlash += flash
        grid.map { row -> row.map { it.reset() } }
        if (grid.flatten().groupBy { it.intensity }.size == 1) {
            return step
        }
    }
    return nFlash
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
