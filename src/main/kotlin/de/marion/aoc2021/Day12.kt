package de.marion.aoc2021

import de.marion.common.runaoc

private const val DAY = 12
private const val PART_ONE_TEST_RESULT: Int = 19
private const val PART_TWO_TEST_RESULT: Int = 103

private const val PART_ONE_ACTUAL_RESULT: Int = 5076
private const val PART_TWO_ACTUAL_RESULT: Int = 145643

private fun part1(input: List<String>): Number {
    val net = input.map { it.split("-") }.map { it[0] to it[1] }
    val caves = Caves(net)

    return caves.getNumberOfWays("start", "end", emptyList(), false)

}

class Caves(net: List<Pair<String, String>>) {

    private var map: Map<String, Set<String>>
    private var ways: MutableList<List<String>> = mutableListOf()

    init {
        this.map = (net + net.map { it.second to it.first })
            .groupBy { it.first }
            .map { it.key to it.value.map { i -> i.second }.toSet() }
            .toMap()
    }

    fun getNumberOfWays(from: String, to: String, visitedBefore: List<String>, part2: Boolean): Int {
        val visitedComplete = visitedBefore + from
        if (from == to) {
            ways.add(visitedComplete)
            return 1
        }

        val numWays = map[from]!!.map {
            if (isUpper(it) || canVisitAgain(visitedComplete, it, part2))
                getNumberOfWays(it, to, visitedComplete, part2)
            else 0 // Sackgasse
        }
        return numWays.sumOf { it }
    }

    private fun canVisitAgain(visited: List<String>, it: String, part2: Boolean): Boolean {
        return if (!part2) {
            !visited.contains(it)
        } else {
            it != "start" && !hasDoubleSmallCave(visited) || !visited.contains(it)
        }
    }

    private fun hasDoubleSmallCave(visited: List<String>): Boolean {
        //"start","kj","HN","dc","HN","kj","dc","end" -> {ArrayList@1081}  size = 1
        val smallCaves = visited.filter { it != it.uppercase() }
        return smallCaves.toSet().size != smallCaves.size
    }


    private fun isUpper(it: String) = it.uppercase() == it
    fun getWays(): List<List<String>> {
        return ways.toList()
    }

}


private fun part2(input: List<String>): Number {
    val net = input.map { it.split("-") }.map { it[0] to it[1] }
    val caves = Caves(net)
    return caves.getNumberOfWays("start", "end", emptyList(), true)
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
