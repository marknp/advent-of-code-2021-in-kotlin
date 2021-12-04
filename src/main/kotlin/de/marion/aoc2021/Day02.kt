data class Position(val x: Int, val y: Int)
data class SubmarineState(val position: Position, val aim: Int)

private const val DAY = 2
private const val PART_ONE_TEST_RESULT = 150
private const val PART_TWO_TEST_RESULT = 900

private const val PART_ONE_ACTUAL_RESULT = 1670340
private const val PART_TWO_ACTUAL_RESULT = 1954293920

private fun part1(input: List<String>): Int {
    val positions = mutableListOf(Position(0, 0))
    for (move in input.map { it.split(" ") }) {
        val last = positions.last()
        val next = when (move[0]) {
            "forward" -> Position(last.x + move[1].toInt(), last.y)
            "down" -> Position(last.x, last.y + move[1].toInt())
            "up" -> Position(last.x, last.y - move[1].toInt())
            else -> throw IllegalArgumentException()
        }
        positions.add(next)
    }
    return positions.last().x * positions.last().y
}

private fun part2(input: List<String>): Int {
    var state = SubmarineState(Position(0, 0), 0)
    for (move in input.map { it.split(" ") }) {
        val value = move[1].toInt()
        val position = state.position

        state = when (move[0]) {
            "forward" -> SubmarineState(
                Position(position.x + value, position.y + state.aim * value), state.aim
            )
            "down" -> SubmarineState(position, state.aim + value)
            "up" -> SubmarineState(position, state.aim - value)
            else -> throw IllegalArgumentException()
        }
    }
    return state.position.x * state.position.y
}


fun main() {
    runaoc(
        DAY,
        PART_ONE_TEST_RESULT,
        PART_ONE_ACTUAL_RESULT,
        PART_TWO_TEST_RESULT,
        PART_TWO_ACTUAL_RESULT,
        { input -> part1(input) },
        { input -> part2(input) }
    )
}