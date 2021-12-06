package de.marion.aoc2015

import de.marion.common.Challenge
import java.lang.Integer.max
import kotlin.system.measureTimeMillis

typealias Coord = Pair<Int, Int>

abstract class Grid {
    abstract fun doIt(instruction: Day6.Instruction)
    abstract fun getCount(): Number
}

class GridBoolean(private val size: Int) : Grid() {
    private val grid = Array(size) { BooleanArray(size) { false } }

    override fun doIt(instruction: Day6.Instruction) {
        for (i in instruction.from.first..instruction.to.first) {
            for (j in instruction.from.second..instruction.to.second) {
                when (instruction.type) {
                    Day6.Type.TURN_ON -> grid[i][j] = true
                    Day6.Type.TURN_OFF -> grid[i][j] = false
                    Day6.Type.TOGGLE -> grid[i][j] = grid[i][j] xor true
                }
            }
        }
    }

    override fun getCount(): Number {
        return grid.sumOf { row -> row.count { it } }
    }
}


class GridInt(private val size: Int) : Grid() {
    private val grid = Array(size) { IntArray(size) { 0 } }

    override fun doIt(instruction: Day6.Instruction) {
        for (i in instruction.from.first..instruction.to.first) {
            for (j in instruction.from.second..instruction.to.second) {
                when (instruction.type) {
                    Day6.Type.TURN_ON -> grid[i][j] += 1
                    Day6.Type.TURN_OFF -> grid[i][j] = max(grid[i][j] - 1, 0)
                    Day6.Type.TOGGLE -> grid[i][j] += 2
                }
            }
        }
    }

    override fun getCount(): Number {
        return grid.sumOf(IntArray::sum)
    }
}

object Day6 : Challenge("--- Day 6: Probably a Fire Hazard ---", "Day06.txt") {
    enum class Type {
        TURN_ON, TURN_OFF, TOGGLE;

        companion object {
            fun from(string: String): Type {
                return valueOf(string.uppercase().replace(" ", "_"))
            }
        }
    }

    data class Instruction(val type: Type, val from: Coord, val to: Coord)

    private val parsed = input.split("""\n""".toRegex())
        .mapNotNull { """([\w ]*) (\d*),(\d*) through (\d*),(\d*)""".toRegex().matchEntire(it) }
        .map { it.groupValues.subList(1, 6) }.map { (type, x1, y1, x2, y2) ->
            Instruction(
                Type.from(type), x1.toInt() to y1.toInt(), x2.toInt() to y2.toInt()
            )
        }

    override fun part1() = measureTimeMillis { solve(GridBoolean(1000)) }


    override fun part2() = measureTimeMillis { solve(GridInt(1000)) }

    private fun solve(grid: Grid): Number {
        parsed.forEach {
            grid.doIt(it)
        }
        return grid.getCount()
    }
}

fun main() {
    Day6.printSolutions()
}