private const val DAY = 3
private const val PART_ONE_TEST_RESULT = 198
private const val PART_TWO_TEST_RESULT = -1

private const val PART_ONE_ACTUAL_RESULT = 3374136
private const val PART_TWO_ACTUAL_RESULT = -1

private fun part1(input: List<String>): Int {
    val gamma = gamma(input)
    val eps = eps(input)
    return gamma * eps

}

private fun gamma(input: List<String>): Int {
    val len = input.size
    val s = (0 until input[0].length)
        .map { i ->
            input.map { inp -> inp[i].toString().toInt() }
                .sum() > (len / 2)
        }.map { it.compareTo(false) }
        .joinToString("")

    return Integer.parseInt(s, 2)
}

private fun eps(input: List<String>): Int {
    val len = input.size
    val s = (0 until input[0].length)
        .map { i ->
            input.map { inp -> inp[i].toString().toInt() }
                .sum() < (len / 2)
        }.map { it.compareTo(false) }
        .joinToString("")

    return Integer.parseInt(s, 2)
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
