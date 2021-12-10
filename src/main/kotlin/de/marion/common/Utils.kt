import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String, folder: String) = File("src/main/resources/de/marion/$folder", "$name.txt").readLines()


/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun runaoc(
    day: Int,
    year: Int,
    part1TestResult: Number,
    part1ActualResult: Number,
    partTwoTestResult: Number,
    partTwoActualResult: Number,
    part1: (List<String>) -> Number,
    part2: (List<String>) -> Number,
) {
    val d = String.format("%02d", day)
    val inputTest = readInput("Day${d}_test", "aoc$year")
    val input = readInput("Day$d", "aoc$year")

    val part11 = part1(inputTest)
    println("Part 1 test: $part11")
    check(part11.equals(part1TestResult))

    val part1Result = part1(input)
    println("Part 1: $part1Result")
    check(part1Result.equals(part1ActualResult))


    if (partTwoTestResult != -999) {
        val part2TestResult = part2(inputTest)
        println("Part 2 test: $part2TestResult")
        check(part2TestResult == partTwoTestResult)
    }

    val part2Result = part2(input)
    println("Part 2: $part2Result")
    check(part2Result == partTwoActualResult)
}
