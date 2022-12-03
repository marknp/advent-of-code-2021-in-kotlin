package de.marion.aoc2022

import de.marion.common.readInputLines

fun main() {
    fun part1(input: List<String>): Int {
        val moves = input.map { it.toMove() }
        val results: List<Int> = moves.map { it.evaluate() }

        return results.sum()


    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputLines("Day02_test", "aoc2022")
    check(part1(testInput) == 15)

    val input = readInputLines("Day02", "aoc2022")
    val part1 = part1(input)
    println("Part 1: $part1")
    check(part1(input) == 11767)

}

private fun String.toMove(): Move {
    val parsed = this.split(" ")
    return Move(Selection.from(parsed[0]), Selection.from(parsed[1]))
}

enum class Selection(val shapeScore: Int) {
    Rock(1), Paper(2), Scissors(3);

    companion object {
        fun from(string: String): Selection {
            return when (string) {
                "A", "X" -> Rock
                "B", "Y" -> Paper
                "C", "Z" -> Scissors
                else -> {
                    throw AOCException()
                }
            }
        }
    }
}

class AOCException : RuntimeException() {}

data class Move(val opponent: Selection, val myMove: Selection) {
    fun evaluate(): Int {
        val winner = when (myMove) {
            Selection.Rock -> when (opponent) {
                Selection.Rock -> Winner.Draw
                Selection.Paper -> Winner.Opponent
                Selection.Scissors -> Winner.I
            }

            Selection.Paper -> when (opponent) {
                Selection.Rock -> Winner.I
                Selection.Paper -> Winner.Draw
                Selection.Scissors -> Winner.Opponent
            }

            Selection.Scissors -> when (opponent) {
                Selection.Rock -> Winner.Opponent
                Selection.Paper -> Winner.I
                Selection.Scissors -> Winner.Draw
            }
        }

        return myMove.shapeScore + winner.winnerScore
    }
}

enum class Winner(val winnerScore: Int) {
    I(6), Draw(3), Opponent(0)
}