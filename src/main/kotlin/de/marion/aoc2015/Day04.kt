package de.marion.aoc2015

import de.marion.common.md5

fun main() {
    //val secret = "bgvyzdsv"
    check(getMinValueForMd5("bgvyzdsv", 27).equals(1038736))
    check(getMinValueForMd5("abcdef", 28).equals(609043))
    check(getMinValueForMd5("pqrstuv", 28).equals(1048970))
    check(getMinValueForMd5("bgvyzdsv", 28).equals(254575))


}

fun getMinValueForMd5(secret: String, maxLength: Int): Int {
    for (suffix in 0..Int.MAX_VALUE) {
        val s = secret + suffix
        val md5 = s.md5()
        //   println(md5)
        if (md5.length < maxLength) {
            println("Found it! $suffix")
            return (suffix)

        }
        if (suffix % 100000 == 0) {
            println("testing $s, md5 is $md5")
        }
    }
    return -1
}