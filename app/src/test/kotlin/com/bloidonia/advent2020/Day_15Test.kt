package com.bloidonia.advent2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day_15Test {

    @Test
    fun `example for part 1`() {
        assertEquals(0, Day_15(listOf(0, 3, 6)).playUntil(4))
        assertEquals(3, Day_15(listOf(0, 3, 6)).playUntil(5))
        assertEquals(3, Day_15(listOf(0, 3, 6)).playUntil(6))
        assertEquals(1, Day_15(listOf(0, 3, 6)).playUntil(7))
        assertEquals(0, Day_15(listOf(0, 3, 6)).playUntil(8))
        assertEquals(4, Day_15(listOf(0, 3, 6)).playUntil(9))
        assertEquals(0, Day_15(listOf(0, 3, 6)).playUntil(10))

        assertEquals(1, Day_15(listOf(1, 3, 2)).playUntil(2020))
        assertEquals(10, Day_15(listOf(2, 1, 3)).playUntil(2020))
        assertEquals(27, Day_15(listOf(1, 2, 3)).playUntil(2020))
    }

}