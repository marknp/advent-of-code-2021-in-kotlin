import java.lang.Integer.min

private const val DAY = 4
private const val PART_ONE_TEST_RESULT = 4512
private const val PART_TWO_TEST_RESULT = 1924

private const val PART_ONE_ACTUAL_RESULT = 58838
private const val PART_TWO_ACTUAL_RESULT = 6256

private fun part1(input: List<String>): Int {
    val parsed = parseData(input)
    val fields = parsed.second
    val moves = parsed.first
    val firstWins = fields.map { it.findFirstWin(moves) }
    val winningMove = firstWins.minOf { it }
    val winningField = fields.get(firstWins.indexOf(winningMove))
    return moves[winningMove] * winningField.getValue(moves.subList(0, moves.find { it.equals(winningMove) }!! +1))
}

fun parseData(input: List<String>): Pair<List<Int>, List<BingoField>> {
    val moves = input.get(0).split(",").map { it.toInt() }

    val bingos = input.subList(2, input.size)
        .map { line -> line.split(" ").filter { it.isNotBlank() }.map { it.toInt() } }
        .filter { it.isNotEmpty() }
    val n = bingos.get(0).size
    val fields = bingos.chunked(n).map { BingoField(it) }
    return Pair(moves, fields)
}

class BingoField(private val field: List<List<Int>>) {
    fun findFirstWin(moves: List<Int>): Int {
        val firstmoves = field.map { line -> line.map { moves.indexOf(it) } }
        val rowWinRounds = firstmoves.map { row -> row.maxOf { it } }
        val colWinRounds = (field.indices).map { i -> firstmoves.maxOf { it[i] } }
        return min(rowWinRounds.minOf { it }, colWinRounds.minOf { it })
    }

    fun getValue(moves: List<Int>): Int {
        val v: List<Int> =  field.flatten().minus(moves.toSet())
        return v.sum()
    }
}


private fun part2(input: List<String>): Int {
    val parsed = parseData(input)
    val fields = parsed.second
    val moves = parsed.first
    val firstWins = fields.map { it.findFirstWin(moves) }
    val winningMove = firstWins.maxOf { it }
    val winningField = fields.get(firstWins.indexOf(winningMove))
    return moves[winningMove] * winningField.getValue(moves.subList(0, moves.find { it.equals(winningMove) }!! +1))
}

fun main() {
    check(
        DAY,
        PART_ONE_TEST_RESULT,
        PART_ONE_ACTUAL_RESULT,
        PART_TWO_TEST_RESULT,
        PART_TWO_ACTUAL_RESULT,
        { input -> part1(input) },
        { input -> part2(input) }
    )
}
