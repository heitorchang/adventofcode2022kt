package day02

import AdventOfCode
import FileUtil

fun main() {
    println(Day02.part1())
    println(Day02.part2())
}

/**
 * Extra points for the symbol I played
 */
enum class SymbolPoints(val points: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

object Day02 : AdventOfCode {
    // I thought about using enum class for these, but couldn't figure out how to dynamically retrieve a value
    val opponentMove = mapOf<String, String>(
        "A" to "ROCK",
        "B" to "PAPER",
        "C" to "SCISSORS"
    )

    val myMove = mapOf<String, String>(
        "X" to "ROCK",
        "Y" to "PAPER",
        "Z" to "SCISSORS"
    )

    val myStratMovePoints = mapOf<String, Int>(
        "X" to 0,
        "Y" to 3,
        "Z" to 6
    )

    val myStratWinner = mapOf<String, String>(
        "ROCK" to "PAPER",
        "PAPER" to "SCISSORS",
        "SCISSORS" to "ROCK"
    )

    val myStratLoser = mapOf<String, String>(
        "ROCK" to "SCISSORS",
        "PAPER" to "ROCK",
        "SCISSORS" to "PAPER"
    )

    val symbolPoints = mapOf<String, Int>(
        "ROCK" to 1,
        "PAPER" to 2,
        "SCISSORS" to 3
    )

    fun winLoseDrawPoints(opponentSymbol: String, mySymbol: String): Int {
        if (opponentSymbol == mySymbol) {
            return 3
        }
        return when (opponentSymbol) {
            "ROCK" -> if (mySymbol == "PAPER") 6 else 0
            "PAPER" -> if (mySymbol == "SCISSORS") 6 else 0
            "SCISSORS" -> if (mySymbol == "ROCK") 6 else 0
            else -> 0
        }
    }

    fun part1Score(opponentChar: String, myChar: String): Int {
        // having to put !! everywhere is pretty ugly :( but the alternative is putting ? next to types
        val matchPoints = winLoseDrawPoints(opponentMove[opponentChar]!!, myMove[myChar]!!)
        val extraSymbolPoints = symbolPoints[myMove[myChar]]!!
        return matchPoints + extraSymbolPoints
    }

    fun part2Score(opponentChar: String, myChar: String): Int {
        val matchPoints = myStratMovePoints[myChar]!!
        val opponentSymbol = opponentMove[opponentChar]!!
        val extraSymbolPoints = when (myChar) {
            "X" -> symbolPoints[myStratLoser[opponentSymbol]]!!
            "Y" -> symbolPoints[opponentMove[opponentChar]]!!
            "Z" -> symbolPoints[myStratWinner[opponentSymbol]]!!
            else -> 0
        }
        return matchPoints + extraSymbolPoints
    }

    override fun part1(): Int {
        var total = 0
        FileUtil.getFile("day02")
            .useLines { lines ->
                lines.forEach { line ->
                    total += part1Score(line.substring(0, 1), line.substring(2, 3))
                }
            }
        return total
    }

    override fun part2(): Int {
        var total = 0
        FileUtil.getFile("day02")
            .useLines { lines ->
                lines.forEach { line ->
                    total += part2Score(line.substring(0, 1), line.substring(2, 3))
                }
            }
        return total
    }
}
