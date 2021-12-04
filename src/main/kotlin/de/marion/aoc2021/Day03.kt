private const val DAY = 3
private const val PART_ONE_TEST_RESULT = 198
private const val PART_TWO_TEST_RESULT = 230

private const val PART_ONE_ACTUAL_RESULT = 3374136
private const val PART_TWO_ACTUAL_RESULT = 4432698

private fun part1(input: List<String>): Int {
    val gamma = gamma(input)
    val eps = eps(input)
    return gamma * eps
}

val Boolean.int
    get() = if (this) 1 else 0

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
    return Integer.parseInt((0 until input[0].length)
        .map { i -> (input.sumOf { element -> element[i].toString().toInt() } < (input.size / 2.0)).int }
        .joinToString(""), 2)
}

private fun part2(input: List<String>): Int {
    val scrub = scrub(input)
    val oxygen = oxygen(input)
    return scrub * oxygen
}

private fun oxygen(input: List<String>): Int {
    var oxygen = input.map { it.split("").filter { it.isNotBlank() }.map { it.toInt() } }
    for (i in (0 until input[0].length)) {
        val mostCommonValue = (oxygen.sumOf { it[i].toString().toInt() } >= oxygen.size / 2.0).int
        oxygen = oxygen.filter { it[i] == mostCommonValue }
        if (oxygen.size <= 1) {
            break
        }
    }
    val scrubberInBinary: String = oxygen[0]
        .joinToString("")
    return Integer.parseInt(scrubberInBinary, 2)
}

private fun scrub(input: List<String>): Int {
    var scrubber = input.map { it.split("").filter { it.isNotBlank() }.map { it.toInt() } }
    for (i in (0 until input[0].length)) {
        val mostCommonValue = (scrubber.sumOf { it[i].toString().toInt() } < scrubber.size / 2.0).int
        scrubber = scrubber.filter { it[i] == mostCommonValue }
        if (scrubber.size <= 1) {
            break
        }
    }
    val scrubberInBinary: String = scrubber[0]
        .joinToString("")
    return Integer.parseInt(scrubberInBinary, 2)
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
