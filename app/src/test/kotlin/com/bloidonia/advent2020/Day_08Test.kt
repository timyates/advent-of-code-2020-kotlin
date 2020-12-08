package com.bloidonia.advent2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day_08Test {

    val example = """nop +0
                    |acc +1
                    |jmp +4
                    |acc +3
                    |jmp -3
                    |acc -99
                    |acc +1
                    |jmp -4
                    |acc +6""".trimMargin()

    val test = Day_08(example.split("\n"))

    @Test
    fun `example as expected`() {
        assertEquals(5, test.run().second)
    }

    @Test
    fun `part 2`() {
        assertEquals(8, test.bruteForce())
    }

}