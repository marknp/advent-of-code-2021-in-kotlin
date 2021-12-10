package de.marion.aoc2021

import runaoc

private const val DAY = 9
private const val PART_ONE_TEST_RESULT: Int = 15
private const val PART_TWO_TEST_RESULT: Int = 1134

private const val PART_ONE_ACTUAL_RESULT: Int = 603
private const val PART_TWO_ACTUAL_RESULT: Int = 1051087

private fun part1(input: List<String>): Int {
    val p = input.map { it.toCharArray().map { c -> c.toString().toInt() } }
    val points = getLowPoints(p)
    return points.sumOf { 1 + p[it.first][it.second] }
}

private fun Int?.biggerThan(t: Int): Boolean {
    return if (this == null) {
        true
    } else {
        this > t
    }

}

data class Coordinate(var lowPoint: Pair<Int, Int>?, var isBorder: Boolean)

private fun part2(input: List<String>): Int {
    val heightMap = input.map { it.toCharArray().map { c -> c.toString().toInt() } }
    val lowPoints = getLowPoints(heightMap)
    val basins = initBasins(heightMap, lowPoints)

    while (basins.flatMap { row -> row.filter { it == null } }.isNotEmpty()) {
        for (i in heightMap.indices) {
            for (j in heightMap[0].indices) {
                if (basins[i][j] == null) {
                    val neighbourBasin = getNeighbourBasin(i, j, basins)
                    if (neighbourBasin != null) {
                        basins[i][j] = neighbourBasin
                    }
                }
            }
        }
    }

    val basinSizes = basins.flatMap { row -> row.map { it?.lowPoint } }
        .filterNotNull()
        .groupBy { it }
        .map { it.value.size }
    return basinSizes.sortedDescending().take(3).reduce(Int::times)
}

private fun initBasins(
    p: List<List<Int>>,
    lowPoints: MutableList<Pair<Int, Int>>
): MutableList<MutableList<Coordinate?>> {
    val basins: MutableList<MutableList<Coordinate?>> = MutableList(p.size) { MutableList(p[0].size) { null } }
    lowPoints.forEach() { basins[it.first][it.second] = Coordinate(it, false) }
    for (i in p.indices) {
        for (j in p[0].indices) {
            if (p[i][j] == 9) {
                basins[i][j] = Coordinate(null, true)
                continue
            }
        }
    }
    return basins
}

fun getNeighbourBasin(i: Int, j: Int, basins: List<List<Coordinate?>>): Coordinate? {
    for (l in listOf(i - 1 to j, i + 1 to j, i to j + 1, i to j - 1)) {
        val neighbour = basins.getOrNull(l.first)?.getOrNull(l.second)
        if (neighbour != null && !neighbour.isBorder) {
            return neighbour
        }
    }
    return null
}

private fun getLowPoints(p: List<List<Int>>): MutableList<Pair<Int, Int>> {
    val lowPoints = mutableListOf<Pair<Int, Int>>()
    for (i in p.indices) {
        for (j in p[0].indices) {
            val t = p[i][j]
            if (p.getOrNull(i - 1)?.getOrNull(j)?.biggerThan(t) != false &&
                p.getOrNull(i + 1)?.getOrNull(j)?.biggerThan(t) != false &&
                p.getOrNull(i)?.getOrNull(j - 1)?.biggerThan(t) != false &&
                p.getOrNull(i)?.getOrNull(j + 1)?.biggerThan(t) != false
            ) {
                lowPoints.add(i to j)
            }
        }
    }
    return lowPoints
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
