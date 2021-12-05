package de.marion.aoc2021

import runaoc

private const val DAY = 5
private const val PART_ONE_TEST_RESULT = 5
private const val PART_TWO_TEST_RESULT = 12

private const val PART_ONE_ACTUAL_RESULT = 7438
private const val PART_TWO_ACTUAL_RESULT = 21406

private fun part1(input: List<String>): Int {
    val lines = input.map { ("""(\d*),(\d*) -> (\d*),(\d*)""").toRegex().matchEntire(it) }
        .filterNotNull()
        .map { it.groupValues }
        .map { Line(Point(it.get(1).toInt(), it.get(2).toInt()), Point(it.get(3).toInt(), it.get(4).toInt())) }
        .map { it.getAllPointsGrid1() }
    return lines.flatten().groupingBy { it }.eachCount().filter { it.value > 1 }.count()
}

class Line(val spoint: Point, val epoint: Point) {
    fun getAllPointsGrid1(): List<Point> {
        if (spoint.x == epoint.x) {
            return (range(spoint.y, epoint.y)).map { Point(spoint.x, it) }
        }
        if (spoint.y == epoint.y) {
            return range(spoint.x, epoint.x).map { Point(it, spoint.y) }
        }
        return listOf()

    }

    fun getAllPointsGrid2(): List<Point> {
        if (spoint.x == epoint.x) {
            return (range(spoint.y, epoint.y)).map { Point(spoint.x, it) }
        }
        if (spoint.y == epoint.y) {
            return range(spoint.x, epoint.x).map { Point(it, spoint.y) }
        }
        val (leftPoint, rightPoint) = listOf(spoint, epoint).sortedBy { it.x }
        val m = (leftPoint.x - rightPoint.x) / (leftPoint.y - rightPoint.y)
        val list = mutableListOf<Point>()
        var i = 0
        do {
            list.add(Point(leftPoint.x + i, leftPoint.y + i * m))
            i++
        } while (list.last().y != rightPoint.y)
        return list

    }

    private fun range(i: Int, y: Int) = (listOf(i, y)).minOf { it }..(listOf(i, y)).maxOf { it }

}

data class Point(val x: Int, val y: Int) {}

private fun part2(input: List<String>): Int {
    val lines = input.map { ("""(\d*),(\d*) -> (\d*),(\d*)""").toRegex().matchEntire(it) }
        .filterNotNull()
        .map { it.groupValues }
        .map { Line(Point(it.get(1).toInt(), it.get(2).toInt()), Point(it.get(3).toInt(), it.get(4).toInt())) }
        .map { it.getAllPointsGrid2() }
    return lines.flatten().groupingBy { it }.eachCount().filter { it.value > 1 }.count()
}

fun main() {
    runaoc(
        DAY,
        2021,
        PART_ONE_TEST_RESULT,
        PART_ONE_ACTUAL_RESULT,
        PART_TWO_TEST_RESULT,
        PART_TWO_ACTUAL_RESULT,
        { input -> part1(input) },
        { input -> part2(input) }
    )
}
