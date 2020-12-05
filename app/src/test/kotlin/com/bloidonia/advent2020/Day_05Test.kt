package com.bloidonia.advent2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day_05Test {

    val test = Day_05()

    @Test
    fun `seats ok`() {
        assertEquals(Day_05.Seat(70, 7), test.calculate("BFFFBBFRRR"))
        assertEquals(Day_05.Seat(14, 7), test.calculate("FFFBBBFRRR"))
        assertEquals(Day_05.Seat(102, 4), test.calculate("BBFFBBFRLL"))
    }

    @Test
    fun `codes ok`() {
        assertEquals(567, test.calculate("BFFFBBFRRR").id)
        assertEquals(119, test.calculate("FFFBBBFRRR").id)
        assertEquals(820, test.calculate("BBFFBBFRLL").id)
    }

}