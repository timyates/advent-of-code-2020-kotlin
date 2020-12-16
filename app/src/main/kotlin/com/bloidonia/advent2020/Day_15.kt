package com.bloidonia.advent2020

class Day_15(val starting: List<Int>) {

    data class Move(val last: Int, val index: Map<Int, Int>)

    var last = starting.last()
    val index = starting.dropLast(1).mapIndexed { idx, n -> n to (idx + 1) }.toMap().toMutableMap()

    fun playUntil(turn: Int): Int {
        for (curr in starting.size until turn) {
            val prevLast = last
            val lastIdx = index[last]
            last = if (lastIdx == null) 0 else { curr - lastIdx }
            index[prevLast] = curr
        }
        return last
    }
}

fun main() {
    println(Day_15(listOf(9, 3, 1, 0, 8, 4)).playUntil(2020))
    println(Day_15(listOf(9, 3, 1, 0, 8, 4)).playUntil(30000000))
}