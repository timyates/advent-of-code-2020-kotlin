package com.bloidonia.advent2020

class Day_15(val starting: List<Long>) {

    data class Move(val turn: Int, val number: Long)

    val previous = starting.mapIndexed { idx, n -> Move(idx + 1, n) }.toMutableList()

    fun playUntil(turn: Int): Long {
        for (move in starting.size until turn) {
            val (prevTurn, prevNumber) = previous.last()
            val prev = previous.reversed().drop(1).find { it.number == prevNumber }?.turn
            if (prev == null) {
                previous.add(Move(prevTurn + 1, 0L))
            } else {
                previous.add(Move(prevTurn + 1, (prevTurn - prev).toLong()))
            }
        }
        return previous.last().number
    }
}

fun main() {
    println(Day_15(listOf(9, 3, 1, 0, 8, 4)).playUntil(2020))
}