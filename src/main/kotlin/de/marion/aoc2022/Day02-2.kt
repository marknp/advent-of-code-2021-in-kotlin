package de.marion.aoc2022

import de.marion.common.readInputLines

fun main() {
    fun part2(input: List<String>): Int {
        val strategys = input.map { it.toStrategy() }
        val results: List<Int> = strategys.map { it.evaluate() }

        return results.sum()


    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputLines("Day02_test", "aoc2022")
    check(part2(testInput) == 12)

    val input = readInputLines("Day02", "aoc2022")
    val part2 = part2(input)
    println("Part 2: $part2")
    check(part2(input) == 13886)

}


private fun String.toStrategy(): Strategy {
    val asMove = this.toMove()
    val expected = when (asMove.myMove) {
        Selection.Rock -> Winner.Opponent
        Selection.Paper -> Winner.Draw
        Selection.Scissors -> Winner.I
    }
    return Strategy(asMove.opponent, expected)
}

data class Strategy(val opponent: Selection, val expected: Winner) {
    fun evaluate(): Int {
        val myMove = when (expected) {
            Winner.I -> when (opponent) {
                Selection.Rock -> Selection.Paper
                Selection.Paper -> Selection.Scissors
                Selection.Scissors -> Selection.Rock
            }

            Winner.Draw -> when (opponent) {
                Selection.Rock -> Selection.Rock
                Selection.Paper -> Selection.Paper
                Selection.Scissors -> Selection.Scissors
            }

            Winner.Opponent -> when (opponent) {
                Selection.Rock -> Selection.Scissors
                Selection.Paper -> Selection.Rock
                Selection.Scissors -> Selection.Paper
            }
        }

        return myMove.shapeScore + expected.winnerScore

    }
}

private fun String.toMove(): Move {
    val parsed = this.split(" ")
    return Move(Selection.from(parsed[0]), Selection.from(parsed[1]))
}
