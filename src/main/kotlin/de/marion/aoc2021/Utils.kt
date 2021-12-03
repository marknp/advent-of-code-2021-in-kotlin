import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/main/resources", "$name.txt").readLines()


/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun booleanToInt(it: Boolean) = it.compareTo(false)


fun check(
    day: Int,
    part1TestResult: Int,
    part1ActualResult: Int,
    partTwoTestResult: Int,
    partTwoActualResult: Int,
    part1: (List<String>) -> Int,
    part2: (List<String>) -> Int,
) {
    val d = String.format("%02d", day)
    check(part1(readInput("Day${d}_test")) == part1TestResult)

    val part1Result = part1(readInput("Day$d"))
    println("Part 1: $part1Result")
    check(part1Result == part1ActualResult)


    check(part2(readInput("Day${d}_test")) == partTwoTestResult)

    val part2Result = part2(readInput("Day$d"))
    println("Part 2: $part2Result")
    check(part2Result == partTwoActualResult)
}
