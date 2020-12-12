package com.bloidonia.advent2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Day_12Test {

    val commands = """F10
                     |N3
                     |F7
                     |R90
                     |F11""".trimMargin().split("\n")

    @Test
    fun `turning works`() {
        assertEquals(Direction.S, Direction.E.right(90))
        assertEquals(Direction.W, Direction.E.right(180))
        assertEquals(Direction.N, Direction.E.right(270))
        assertEquals(Direction.E, Direction.E.right(360))
        assertEquals(Direction.N, Direction.E.left(90))
        assertEquals(Direction.W, Direction.E.left(180))
        assertEquals(Direction.S, Direction.E.left(270))
        assertEquals(Direction.E, Direction.E.left(360))
    }

    @Test
    fun `example for part1`() {
        val day12 = Day_12()
        val result = commands.fold(Ship()) { current, command ->
            day12.move(current, command)
        }
        assertEquals(25, result.position.manhattan())
    }

    @Test
    fun `example for part2`() {
        val day12 = Day_12()
        val result = commands.fold(ShipAndWaypoint()) { current, command ->
            println(current)
            day12.move(current, command)
        }
        assertEquals(286, result.ship.position.manhattan())
    }

}