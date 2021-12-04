import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/main/resources/de/marion/aoc2021", "$name.txt").readLines()


/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun runaoc(
    day: Int,
    part1TestResult: Int,
    part1ActualResult: Int,
    partTwoTestResult: Int,
    partTwoActualResult: Int,
    part1: (List<String>) -> Int,
    part2: (List<String>) -> Int,
) {
    val d = String.format("%02d", day)
    val inputTest = readInput("Day${d}_test")
    val input = readInput("Day$d")

    check(part1(inputTest) == part1TestResult)

    val part1Result = part1(input)
    println("Part 1: $part1Result")
    check(part1Result == part1ActualResult)


    check(part2(inputTest) == partTwoTestResult)

    val part2Result = part2(input)
    println("Part 2: $part2Result")
    check(part2Result == partTwoActualResult)
}