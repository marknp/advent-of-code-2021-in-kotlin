package de.marion.aoc2015

import runaoc

private const val DAY = 7
private const val PART_ONE_TEST_RESULT = 72
private const val PART_TWO_TEST_RESULT = -999

private const val PART_ONE_ACTUAL_RESULT = 46065
private const val PART_TWO_ACTUAL_RESULT = 3783758

private fun part1(input: List<String>): Int {
    return solve1(parse(input))
}

private fun part2(input: List<String>): Int {
    val parse = parse(input)
    //val a = solve1(parse)
    val toMutableMap = parse.first.toMutableMap()
    toMutableMap["b"] = 46065.toUShort()
    return solve1(toMutableMap to parse.second)
}

fun solve1(data: Pair<Map<String, UShort>, List<Gate>>): Int {
    val values = data.first.toMutableMap()
    val g = data.second.toMutableList()
    while (!values.containsKey("a")) {
        val newGates = g.filter { gate ->
            gate.input.input.all { values.keys.contains(it) || isNumeric(it) }
        }
        values.putAll(newGates.associate { gate ->
            gate.out to runOperation(
                gate.input.input.map { v -> values[v] ?: v.toUShort() }, gate.input.operation
            )
        })
        g.removeAll(newGates)
    }
    return values["a"]?.toInt() ?: 0
}

private fun isNumeric(it: String) = """\d*""".toRegex().matches(it)

fun runOperation(input: List<UShort>, operation: Operation?): UShort {
    return when (operation) {
        null -> input[0]
        Operation.AND -> input[0].and(input[1])
        Operation.OR -> input[0].or(input[1])
        Operation.NOT -> input[0].inv()
        Operation.RSHIFT -> input[0].toUInt().shr(input[1].toInt()).toUShort()
        Operation.LSHIFT -> input[0].toUInt().shl(input[1].toInt()).toUShort()
    }
}

private fun parse(input: List<String>): Pair<Map<String, UShort>, List<Gate>> {
    val parsed = input.map { it.split(" -> ") }
        .groupBy { isNumeric(it[0]) }
    val inp = parsed[true]!!.associate { row -> row[1] to row[0].toUShort() }
    val gates = parsed[false]!!.map { Gate(out = it[1], input = In(it[0].split(" "))) }
    return Pair(inp, gates)
}

class In(split: List<String>) {
    var operation: Operation? = null
    var input: List<String> = listOf()

    init {
        when (split.size) {
            1 -> input = listOf(split[0])
            2 -> {
                input = listOf(split[1])
                operation = Operation.valueOf(split[0])
            }
            3 -> {
                operation = Operation.valueOf(split[1])
                input = listOf(split[0], split[2])
            }
        }
    }

}

enum class Operation {
    NOT, AND, LSHIFT, RSHIFT, OR;
}

class Gate(val out: String, val input: In)


fun main() {
    runaoc(DAY,
        2015,
        PART_ONE_TEST_RESULT,
        PART_ONE_ACTUAL_RESULT,
        PART_TWO_TEST_RESULT,
        PART_TWO_ACTUAL_RESULT,
        { input -> part1(input) },
        { input -> part2(input) })
}