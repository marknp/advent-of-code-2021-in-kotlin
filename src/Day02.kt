data class Position(val x: Int, val y: Int)

fun main() {
    fun part1(input: List<String>): Int {
        val positions = mutableListOf(Position(0, 0))
        for (move in input.map { it.split(" ") }) {
            val last = positions.last()
            val next = when (move.get(0)) {
                "forward" -> Position(last.x + move.get(1).toInt(), last.y)
                "back" -> Position(last.x - move.get(1).toInt(), last.y)
                "down" -> Position(last.x, last.y + move.get(1).toInt())
                "up" -> Position(last.x, last.y - move.get(1).toInt())
                else -> TODO()
            }
            positions.add(next)
        }
        return positions.last().x * positions.last().y
    }

    fun part2(input: List<String>): Int {
        TODO()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)

    val part1 = part1(readInput("Day02"))
    println("Part 1: $part1")
    check(part1 == 1670340)

/*
    val part2test = part2(testInput)
    check(part2test == 5)
    val part2 = part2(input)
    println("Part 2: $part2")
    check(part2(input) == 1797)

    */
}
