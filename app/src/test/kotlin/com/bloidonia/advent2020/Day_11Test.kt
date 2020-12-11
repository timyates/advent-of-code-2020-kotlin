package com.bloidonia.advent2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Day_11Test {

    val seating = """L.LL.LL.LL
                    |LLLLLLL.LL
                    |L.L.L..L..
                    |LLLL.LL.LL
                    |L.LL.LL.LL
                    |L.LLLLL.LL
                    |..L.L.....
                    |LLLLLLLLLL
                    |L.LLLLLL.L
                    |L.LLLLL.LL""".trimMargin()

    val plan = Day_11.loadSeatingPlan(seating)

    @Test
    fun `example as expected`() {
        assertEquals(plan.seats.size, seating.count { it == 'L' })
    }

    @Test
    fun `adjacency works`() {
        // Two seats on the row below
        val adjacentSeats = plan.adjacency(plan.seats, 0, 0)
        assertEquals(listOf(0, 1), adjacentSeats.map { it.x })
        assertEquals(listOf(1, 1), adjacentSeats.map { it.y })
    }

    @Test
    fun `diagonal adjacency works`() {
        // Two seats on the row below
        val adjacentSeats = plan.adjacency(plan.seats, 7, 2)
        assertEquals(listOf(6, 8, 6, 8), adjacentSeats.map { it.x })
        assertEquals(listOf(1, 1, 3, 3), adjacentSeats.map { it.y })
    }

    @Test
    fun `steady state achieved for part 1 example`() {
        assertEquals(37, plan.seatsAfterSteadyState(4, plan::adjacency))
    }

    @Test
    fun `line of sight adjacency`() {
        val adjacentSeats = plan.part2Adjacency(plan.seats, 7, 2)
        assertEquals(listOf(6, 8, 4, 6, 7, 8), adjacentSeats.map { it.x })
        assertEquals(listOf(1, 1, 2, 3, 7, 3), adjacentSeats.map { it.y })
    }

    @Test
    fun `steady state achieved for part 2 example`() {
        assertEquals(26, plan.seatsAfterSteadyState(5, plan::part2Adjacency))
    }

}