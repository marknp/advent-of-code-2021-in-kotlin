package de.marion.aoc2021

import runaoc

private const val DAY = 8
private const val PART_ONE_TEST_RESULT: Int = 26
private const val PART_TWO_TEST_RESULT: Int = 61229

private const val PART_ONE_ACTUAL_RESULT: Int = 530
private const val PART_TWO_ACTUAL_RESULT: Int = 1051087

private fun part1(input: List<String>): Int {
    val parsed = parse8(input)
    return parsed.flatMap { it.toNumbers(true) }.count()
}

private fun part2(input: List<String>): Int {
    val parsed = parse8(input)
    val flatMap = parsed.map { it.toNumbers(false).map { it.toString() } }.map { it.joinToString("").toInt() }
    return flatMap.sum()
}

fun parse8(input: List<String>): List<Segment> {
    return input.map { line -> line.split(" | ") }
        .map { (i, o) -> Segment(i.split(" ").map { sortStringInline(it) }, o.split(" ").map { sortStringInline(it) }) }

}

private fun sortStringInline(it: String) = it.toCharArray().sorted().joinToString("")

class Segment(val input: List<String>, private val output: List<String>) {
    private val wires: MutableMap<SegId, Char> = mutableMapOf()
    private val numbers: MutableMap<String, Int> = mutableMapOf()


    enum class SegId {
        AA, BB, CC, DD, EE, FF, GG

    }

    fun toNumbers(part1: Boolean): List<Int> {
        return if (part1) {
            output.mapNotNull { toNumberSimple(it) }

        } else {
            createMap()
            output.map { toNumber(it) }
        }
    }

    // length to numbers
    // 2 -> 1
    // 3 -> 7
    // 4 -> 4
    // 5 -> 2, 3, 5,
    // 6 -> 0, 9, 6
    // wire segment how often?
    // AA: 8, BB: 6, CC: 8, DD: 7, EE: 4, FF: 9, GG: 7
    private fun createMap() {
        val seven = input.first { it.length == 3 }
        val one = input.first { it.length == 2 }
        val four = input.first { it.length == 4 }
        val eight = input.first { it.length == 7 }

        val groupedInput =
            input.flatMap { it.toCharArray().toList() }.groupBy { it }.map { it.key to it.value.count() }.toMap()

        this.wires[SegId.AA] = (seven.toCharArray().toSet() - one.toCharArray().toSet()).first()
        this.wires[SegId.BB] = groupedInput.filter { it.value == 6 }.keys.first()
        this.wires[SegId.EE] = groupedInput.filter { it.value == 4 }.keys.first()
        this.wires[SegId.CC] = groupedInput.filter { it.value == 8 }.filter { it.key != wires[SegId.AA] }.keys.first()
        this.wires[SegId.FF] = groupedInput.filter { it.value == 9 }.keys.first()
        this.wires[SegId.DD] =
            (four.toCharArray().toSet() - setOfNotNull(wires[SegId.BB], wires[SegId.CC], wires[SegId.FF])).first()
        this.wires[SegId.GG] = ("abcdefg".toCharArray().toSet() - wires.values.toSet()).first()


        val zero = listOf(
            wires[SegId.AA], wires[SegId.BB], wires[SegId.CC], wires[SegId.EE], wires[SegId.FF], wires[SegId.GG]
        ).map { it.toString() }.sorted().joinToString("")
        val two = listOf(
            wires[SegId.AA], wires[SegId.DD], wires[SegId.CC], wires[SegId.EE], wires[SegId.GG]
        ).map { it.toString() }.sorted().joinToString("")
        val three = listOf(
            wires[SegId.AA], wires[SegId.DD], wires[SegId.CC], wires[SegId.FF], wires[SegId.GG]
        ).map { it.toString() }.sorted().joinToString("")
        val five = listOf<Char?>(
            wires[SegId.AA], wires[SegId.DD], wires[SegId.BB], wires[SegId.FF], wires[SegId.GG]
        ).map { it.toString() }.sorted().joinToString("")
        val six = listOf<Char?>(
            wires[SegId.AA], wires[SegId.DD], wires[SegId.BB], wires[SegId.FF], wires[SegId.GG], wires[SegId.EE]
        ).map { it.toString() }.sorted().joinToString("")
        val nine = listOf<Char?>(
            wires[SegId.AA], wires[SegId.CC], wires[SegId.BB], wires[SegId.FF], wires[SegId.GG], wires[SegId.DD]
        ).map { it.toString() }.sorted().joinToString("")

        numbers[four] = 4
        numbers[seven] = 7
        numbers[one] = 1
        numbers[eight] = 8
        numbers[zero] = 0
        numbers[two] = 2
        numbers[three] = 3
        numbers[five] = 5
        numbers[six] = 6
        numbers[nine] = 9

        if (wires.values.sorted().joinToString("") != "abcdefg") {
            throw IllegalArgumentException()
        }

    }

    private fun toNumber(it: String): Int {
        return getValue(it)
    }

    private fun getValue(it: String): Int {
        return numbers[it] ?: 0
    }

    private fun toNumberSimple(it: String): Int? {
        return when (it.length) {
            2 -> 1
            4 -> 4
            3 -> 7
            7 -> 8
            else -> null
        }
    }

}


fun main() {
    test()
    runaoc(DAY,
        2021,
        PART_ONE_TEST_RESULT,
        PART_ONE_ACTUAL_RESULT,
        PART_TWO_TEST_RESULT,
        PART_TWO_ACTUAL_RESULT,
        { input -> part1(input) },
        { input -> part2(input) })
}

fun test() {
    val input = listOf("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf")
    val parse8 = parse8(input)
    val flatMap = parse8.map { it.toNumbers(false).map { it.toString() } }.map { it.joinToString("").toInt() }
    val c = flatMap.sum()
    check(flatMap.sum() == 5353)
}