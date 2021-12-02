private const val DAY = 3
private const val PART_ONE_TEST_RESULT = 150
private const val PART_TWO_TEST_RESULT = 900

private const val PART_ONE_ACTUAL_RESULT = -1
private const val PART_TWO_ACTUAL_RESULT = -1

private fun part1(input: List<String>): Int {
    throw NotImplementedError("TODO Part 1")
}

private fun part2(input: List<String>): Int {
    throw NotImplementedError("TODO Part 2")
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
