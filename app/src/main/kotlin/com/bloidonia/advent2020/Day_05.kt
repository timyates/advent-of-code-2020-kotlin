package com.bloidonia.advent2020

import com.bloidonia.linesFromResource

class Day_05 {
    fun calculate(input: String) =
        Seat(
            decode(input.take(8), 0, 127),
            decode(input.takeLast(3), 0, 7),
        )

    private tailrec fun decode(commands: String, lower: Int, upper: Int): Int {
        if (commands.isEmpty()) {
            return lower;
        }
        val diff = (upper - lower + 1) / 2
        var newLower = lower
        var newUpper = upper
        when (commands.take(1)) {
            "F", "L" -> {
                newUpper -= diff
            }
            "B", "R" -> {
                newLower += diff
            }
            else -> {
                throw IllegalArgumentException("Unknown command at head of $commands")
            }
        }
        return decode(commands.drop(1), newLower, newUpper)
    }

    data class Seat(val row: Int, val col: Int) {
        val id: Int = row * 8 + col
    }

}

fun main() {
    val day05 = Day_05()
    val passes = linesFromResource("/5.txt")
    val seats = passes.map { day05.calculate(it) }
    val ids = seats.map { it.id }
    val maxId = ids.maxOrNull()!!

    // Part 1
    println(maxId)

    // Find all the ids that are missing
    val allMissingIds = (0..maxId).filter { it !in ids }

    // Ours has a seat either side of it
    val mySeat = allMissingIds.find { ids.contains(it - 1) && ids.contains(it + 1) }

    println(mySeat)

}