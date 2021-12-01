import java.util.stream.IntStream

fun main() {
    fun part1(input: List<String>): Int {
        val inputAsNumbers = input.map { string -> Integer.valueOf(string) }
        var result = 0
        for (i in IntStream.rangeClosed(1, input.size - 1)) {
            if (inputAsNumbers[i] > inputAsNumbers[i - 1]) {
                result++
            }
        }
        return result
    }

    fun threeMeasurementSum(i: Int, numbers: List<Int>): Int {
        return numbers[i - 1] + numbers[i] + numbers[i + 1]
    }

    fun part2(input: List<String>): Int {
        val inputAsNumbers = input.map { string -> Integer.valueOf(string) }
        var result = 0
        for (i in IntStream.rangeClosed(2, input.size - 2)) {
            if (threeMeasurementSum(i, inputAsNumbers) > threeMeasurementSum(i - 1, inputAsNumbers)) {
                result++
            }
        }
        return result
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    val part1 = part1(input)
    println("Part 1: " + part1)
    check(part1(input) == 1766)

    check(part2(testInput) == 5)
    val part2 = part2(input)
    println("Part 2: " + part2)
    check(part2(input) == 1797)
}
