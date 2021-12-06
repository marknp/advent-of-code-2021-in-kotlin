package de.marion.aoc2015

import de.marion.common.Challenge


object Day10 : Challenge("--- Day 10: Not a Fire Hazard ---", "Day10.txt") {
    override fun part1(): Any {
        var last = input
        for (i in 1..50) {
            println(i.toString() + " " + last.length)
            var thiss = StringBuilder()
            var next: Char? = null
            var counter = 0
            for (c in last) {
                if (next == null) {
                    next = c
                    counter++
                } else if (c == next) {
                    counter++
                } else {
                    thiss = thiss.append(counter).append(next)
                    next = c
                    counter = 1
                }
            }
            last = thiss.append(counter).append(next).toString()
        }
        return last.length
    }

    override fun part2(): Any? {
        TODO("Not yet implemented")
    }

}

fun main() {
    Day10.printSolutions()
}