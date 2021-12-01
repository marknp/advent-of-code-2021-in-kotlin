import java.util.stream.IntStream

fun main() {
    fun part1(input: List<String>): Int {
        val inputAsNumbers = input.map { string -> Integer.valueOf(string) }
        var sum = 0
        for (i in IntStream.rangeClosed(1, input.size - 1)) {
            if (inputAsNumbers[i] > inputAsNumbers[i - 1]) {
                sum++
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println("Part 1: " + part1(input))
    println("Part 1: " + part2(input))
}
