package day03

import AdventOfCode

fun main() {
    println(Day03.part1())
    println(Day03.part2())
}

object Day03 : AdventOfCode {
    val alphabet = "_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    fun getUniqueChars(s: String): Map<Char, Int> {
        var map = mutableMapOf<Char, Int>()
        s.forEach { map[it] = 1 }
        return map
    }

    override fun part1(): Int {
        var total = 0
        FileUtil.getFile("day03")
            .useLines { lines ->
                lines.forEach { line ->
                    val halfLen = line.length / 2
                    val leftHalf = getUniqueChars(line.substring(0, halfLen))
                    val rightHalf = getUniqueChars(line.substring(halfLen))
                    val common = leftHalf.keys.intersect(rightHalf.keys)
                    total += alphabet.indexOf(common.first())
                }
            }
        return total
    }

    override fun part2(): Int {
        FileUtil.getFile("day03")
            .useLines { lines ->
                lines.forEach { line ->

                }
            }
        return 0
    }
}