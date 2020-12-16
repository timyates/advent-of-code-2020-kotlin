package com.bloidonia.advent2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day_14Test {

    val example = """mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
                    |mem[8] = 11
                    |mem[7] = 101
                    |mem[8] = 0""".trimMargin().split("\n")

    val test = Day_14()

    @Test
    fun `check example`() {
        assertEquals(73, Day_14.Masks("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X").process(11))
        assertEquals(101, Day_14.Masks("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X").process(101))
        assertEquals(64, Day_14.Masks("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X").process(0))
    }

    @Test
    fun `part 1 example`() {
        assertEquals(165, test.part1(example))
    }

}