data class Position(val x: Int, val y: Int)
data class SubmarineState(val position: Position, val aim: Int)

fun main() {
    fun part1(input: List<String>): Int {
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

    fun part2(input: List<String>): Int {
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


    check(part1(readInput("Day02_test")) == 150)

    val part1 = part1(readInput("Day02"))
    println("Part 1: $part1")
    check(part1 == 1670340)

    // PART 2
    check(part2(readInput("Day02_test")) == 900)

    val part2 = part2(readInput("Day02"))
    println("Part 2: $part2")
    check(part2 == 1954293920)


}
