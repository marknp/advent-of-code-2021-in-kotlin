package de.marion.aoc2021

import runaoc
import java.lang.Integer.min

private const val DAY = 4
private const val PART_ONE_TEST_RESULT = 4512
private const val PART_TWO_TEST_RESULT = 1924

private const val PART_ONE_ACTUAL_RESULT = 58838
private const val PART_TWO_ACTUAL_RESULT = 6256

private fun part1(input: List<String>): Int {
    val (moves, fields) = parseData(input)
    val winningRounds = fields.map { it.findWinningRound(moves) }
    val winningRound = winningRounds.minOf { it }
    val winner = fields[winningRounds.indexOf(winningRound)]
    return moves[winningRound] * winner.getValue(moves.subList(0, moves.find { it == winningRound }?.plus(1) ?: 0))
}

fun parseData(input: List<String>): Pair<List<Int>, List<BingoField>> {
    val moves = input[0].split(",").map { it.toInt() }

    val bingos = input.subList(2, input.size)
        .map { line ->
            line.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
        }
        .filter { it.isNotEmpty() }
    val n = bingos[0].size
    val fields = bingos.chunked(n).map { BingoField(it) }
    return Pair(moves, fields)
}

class BingoField(private val field: List<List<Int>>) {
    fun findWinningRound(moves: List<Int>): Int {
        val roundOfCrossing = field.map { line -> line.map { moves.indexOf(it) } }
        val rowWinRounds = roundOfCrossing.map { row -> row.maxOf { it } }
        val colWinRounds = (field.indices).map { i -> roundOfCrossing.maxOf { it[i] } }
        return min(rowWinRounds.minOf { it }, colWinRounds.minOf { it })
    }

    fun getValue(moves: List<Int>): Int {
        return field.flatten().minus(moves.toSet()).sum()
    }
}

private fun part2(input: List<String>): Int {
    val parsed = parseData(input)
    val fields = parsed.second
    val moves = parsed.first
    val firstWins = fields.map { it.findWinningRound(moves) }
    val winningMove = firstWins.maxOf { it }
    val winningField = fields[firstWins.indexOf(winningMove)]
    return moves[winningMove] * winningField.getValue(moves.subList(0, moves.find { it == winningMove }!! + 1))
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
