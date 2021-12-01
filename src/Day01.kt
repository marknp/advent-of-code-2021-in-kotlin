fun main() {
    fun part1(input: List<String>): Int {
        val inputAsNumbers = input.map { string -> Integer.valueOf(string) }
        return inputAsNumbers.zipWithNext().count { (a, b) -> b > a }

    }

    fun part2(input: List<String>): Int {
        val inputAsNumbers = input.map { string -> Integer.valueOf(string) }
        val slidingAverage = inputAsNumbers.windowed(3).map { it.sum() }
        return slidingAverage.zipWithNext().count { (a, b) -> b > a }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    val part1 = part1(input)
    println("Part 1: $part1")
    check(part1(input) == 1766)

    val part2test = part2(testInput)
    check(part2test == 5)
    val part2 = part2(input)
    println("Part 2: $part2")
    check(part2(input) == 1797)
}
