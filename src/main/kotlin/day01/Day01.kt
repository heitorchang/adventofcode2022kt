package day01

import AdventOfCode
import FileUtil
import java.lang.Math.max

fun main() {
    println(Day01.part1())
    println(Day01.part2())
}

object Day01 : AdventOfCode {
    override fun part1(): Int {
        var maxCalories = 0
        var currentElf = 0

        FileUtil.getFile("day01")
            .useLines { lines ->
                lines.forEach { line ->
                    if (line.isNullOrEmpty()) {
                        maxCalories = max(maxCalories, currentElf)
                        currentElf = 0
                    } else {
                        currentElf += line.toInt()
                    }
                }
            }
        // consider that last line of lines isn't empty and take into account the last elf
        return max(maxCalories, currentElf)
    }

    override fun part2(): Int {
        var elves = arrayListOf<Int>()
        var currentElf = 0

        FileUtil.getFile("day01")
            .useLines { lines ->
                lines.forEach { line ->
                    if (line.isNullOrEmpty()) {
                        elves.add(currentElf)
                        currentElf = 0
                    } else {
                        currentElf += line.toInt()
                    }
                }
            }
        // consider that last line of lines isn't empty, add the last elf if non-zero
        if (currentElf > 0) {
            elves.add(currentElf)
        }
        elves.sortDescending()
        return elves.slice(0..2).sum()
    }
}