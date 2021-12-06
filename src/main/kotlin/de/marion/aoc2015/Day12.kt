package de.marion.aoc2015

import de.marion.common.Challenge


object Day12 : Challenge("--- Day 10: Not a Fire Hazard ---", "Day12.txt") {
    override fun part1(): Any {
        val nums = """[(-\d)*.*]*""".toRegex()
            .matchEntire(input)?.groupValues?.drop(0)
        return if (nums != null) nums.sumOf { it.toInt() } else 0
    }

    override fun part2(): Any? {
        TODO("Not yet implemented")
    }

}

fun main() {
    Day12.printSolutions()
}