package com.bloidonia.advent2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day_10Test {

    val firstRatings = """16
                         |10
                         |15
                         |5
                         |1
                         |11
                         |7
                         |19
                         |6
                         |12
                         |4""".trimMargin().split("\n").map { it.toInt() }

    val secondRatings = """28
                          |33
                          |18
                          |42
                          |31
                          |14
                          |46
                          |20
                          |48
                          |47
                          |24
                          |23
                          |49
                          |45
                          |19
                          |38
                          |39
                          |11
                          |1
                          |32
                          |25
                          |35
                          |8
                          |17
                          |7
                          |9
                          |4
                          |2
                          |34
                          |10
                          |3""".trimMargin().split("\n").map { it.toInt() }

    @Test
    fun `example as expected`() {

        val test = Day_10(firstRatings)

        assertEquals(listOf(1, 3, 1, 1, 1, 3, 1, 1, 3, 1, 3, 3), test.differences().toList())
    }

    @Test
    fun `example 2 as expected`() {
        val test = Day_10(secondRatings)
        val result = test.differences().toList()

        assertEquals(22, result.count { it == 1 })
        assertEquals(10, result.count { it == 3 })
    }

    @Test
    fun `part 2 as expected`() {
        val a = Day_10(firstRatings)
        assertEquals(8, a.combinations())

        val b = Day_10(secondRatings)
        assertEquals(19208, b.combinations())
    }
}