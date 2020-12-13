package com.bloidonia.advent2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day_13Test {

    val commands = """939
                     |7,13,x,x,59,x,31,19""".trimMargin().split("\n")

    @Test
    fun `first example works`() {
        assertEquals(295L, Day_13(commands[0].toLong(), commands[1]).part1())
    }

    @Test
    fun `test lcm`() {
        assertEquals(12, Day_13.lcm(3, 4))
        assertEquals(12, Day_13.lcm(listOf(2, 3, 4)))
        assertEquals(24, Day_13.lcm(listOf(6, 6, 8)))
    }

    @Test
    fun `second example works`() {
        assertEquals(1068781L, Day_13(commands[0].toLong(), commands[1]).part2())
    }

}