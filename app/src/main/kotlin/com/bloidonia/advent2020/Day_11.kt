package com.bloidonia.advent2020

import com.bloidonia.textFromResource
import java.lang.Integer.max

data class Seat(val x: Int, val y: Int, val occupied: Boolean)

class Plan(val width: Int, val height: Int) {
    var seats: List<Seat> = listOf()

    constructor(width: Int, height: Int, seats: String) : this(width, height) {
        this.seats = seats
            .mapIndexed { idx, ch ->
                if (ch == 'L')
                    Seat(idx.rem(width), idx.div(width), false)
                else
                    null
            }.filterNotNull()
    }

    override fun toString(): String = seats.toString()

    fun adjacency(seats: List<Seat>, x: Int, y: Int): List<Seat> =
        seats.filter { it.x in (x - 1) until (x + 2) && it.y in (y - 1) until (y + 2) }
            .filterNot { (it.x == x) && (it.y == y) }

    fun tick(seatingPlan: List<Seat>, crowdTolerance: Int, adjacencyMethod: (List<Seat>, Int, Int) -> List<Seat>) =
        seatingPlan.map { seat ->
            val occupiedAdjacents = adjacencyMethod(seatingPlan, seat.x, seat.y).count { it.occupied }
            if (seat.occupied && occupiedAdjacents >= crowdTolerance)
                Seat(seat.x, seat.y, false)
            else if (!seat.occupied && occupiedAdjacents == 0)
                Seat(seat.x, seat.y, true)
            else seat
        }

    // Ok, slow blunt brute force hack time...  8 adjacent directions
    //
    // 0 1 2
    // 3   4
    // 5 6 7
    //
    // find them
    fun part2Adjacency(seatingPlan: List<Seat>, x: Int, y: Int): List<Seat> {
        val seats: Array<Seat?> = Array(8) {
            for (i in 1 until max(width, height)) {
                val seat = when (it) {
                    0 -> seatingPlan.find { it.x == x - i && it.y == y - i }
                    1 -> seatingPlan.find { it.x == x && it.y == y - i }
                    2 -> seatingPlan.find { it.x == x + i && it.y == y - i }
                    3 -> seatingPlan.find { it.x == x - i && it.y == y }
                    4 -> seatingPlan.find { it.x == x + i && it.y == y }
                    5 -> seatingPlan.find { it.x == x - i && it.y == y + i }
                    6 -> seatingPlan.find { it.x == x && it.y == y + i }
                    7 -> seatingPlan.find { it.x == x + i && it.y == y + i }
                    else -> null
                }
                if (seat != null) return@Array (seat)
            }
            null
        }
        return seats.toList().filterNotNull()
    }

    fun debug() {
        for (y in 0 until height) {
            for (x in 0 until width) {
                print(seats.find { it.x == x && it.y == y }?.let {
                    if (it.occupied) "#" else "L"
                } ?: ".")
            }
            println()
        }
        println()
    }

    fun seatsAfterSteadyState(crowdTolerance: Int, adjacencyMethod: (List<Seat>, Int, Int) -> List<Seat>): Int {
        // clone our seats
        var localSeats = seats.map { Seat(it.x, it.y, false) }.toList()
        var previous: List<Seat> = listOf()
        while (!previous.equals(localSeats)) {
            previous = localSeats
            localSeats = tick(localSeats, crowdTolerance, adjacencyMethod)
        }
        return localSeats.count { it.occupied }
    }

}

class Day_11 {

    companion object {
        fun loadSeatingPlan(plan: String) = plan.split("\n").let {
            Plan(it[0].length, it.size, it.joinToString(""))
        }
    }

}

fun main() {
    val loadSeatingPlan = Day_11.loadSeatingPlan(textFromResource("/11.txt"))
    println(loadSeatingPlan.seatsAfterSteadyState(4, loadSeatingPlan::adjacency))

    // You have time for a coffee before this returns
    println(loadSeatingPlan.seatsAfterSteadyState(5, loadSeatingPlan::part2Adjacency))
}