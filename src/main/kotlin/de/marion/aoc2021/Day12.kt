package de.marion.aoc2021

import runaoc

private const val DAY = 12
private const val PART_ONE_TEST_RESULT: Int = 19
private const val PART_TWO_TEST_RESULT: Int = 103

private const val PART_ONE_ACTUAL_RESULT: Int = 5076
private const val PART_TWO_ACTUAL_RESULT: Int = 476

private fun part1(input: List<String>): Number {
    val net = input.map { it.split("-") }.map { it[0] to it[1] }
    val caves = Caves(net)

    return caves.getNumberOfTraverses("start", "end", emptyList(), false)

}

class Caves(net: List<Pair<String, String>>) {

    private var map: Map<String, Set<String>>
    var ways: MutableList<List<String>>

    init {
        this.ways = mutableListOf()
        this.map = (net + net.map { it.second to it.first })
            .groupBy { it.first }
            .map { it.key to it.value.map { i -> i.second }.toSet() }
            .toMap()
    }

    fun getNumberOfTraverses(from: String, to: String, visited: List<String>, part2: Boolean): Int {
        if (from == to) {
            ways.add(visited + to)
            return 1
        }

        val edges = map.get(from)
        val numWays =
            edges!!.map {
                if (isUpper(it) || canVisitAgain(visited, it, part2))
                    getNumberOfTraverses(it, to, visited + from, part2)
                else 0
            }
        return numWays.sumOf { it }
    }

    private fun canVisitAgain(visited: List<String>, it: String, part2: Boolean): Boolean {
        return if (!part2) {
            (!visited.contains(it))
        } else {
            (it != "start" && (!hasDoubleSmallCave(visited)) || !visited.contains(it))
        }
    }

    private fun hasDoubleSmallCave(visited: List<String>): Boolean {
        val smallcaves = visited.filter { it != it.uppercase() }
        return smallcaves.toSet().size != smallcaves.size
    }


    fun isUpper(it: String) = it.uppercase() == it

}


private fun part2(input: List<String>): Number {
    val net = input.map { it.split("-") }.map { it[0] to it[1] }
    val caves = Caves(net)

    return caves.getNumberOfTraverses("start", "end", emptyList(), true)
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
