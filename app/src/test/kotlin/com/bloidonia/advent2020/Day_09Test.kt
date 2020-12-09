package com.bloidonia.advent2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day_09Test {

    val example = """35
                    |20
                    |15
                    |25
                    |47
                    |40
                    |62
                    |55
                    |65
                    |95
                    |102
                    |117
                    |150
                    |182
                    |127
                    |219
                    |299
                    |277
                    |309
                    |576""".trimMargin().split("\n").map { it.toLong() }

    val test = Day_09()

    @Test
    fun `example as expected`() {
        assertEquals(127, test.part1(example, 5))
    }

    @Test
    fun `part 2`() {
        assertEquals(62, test.part2(example, 127))
    }

}