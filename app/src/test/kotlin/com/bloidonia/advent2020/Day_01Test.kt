package com.bloidonia.advent2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day_01Test {

    val day1 = Day_01()
    val target = 2020
    val input = listOf(1721, 979, 366, 299, 675, 1456)

    @Test
    fun match2() {
        val match2 = day1.match2(target, input)

        assertEquals(514579, match2, "Example should pass")
    }

    @Test
    fun match3() {
        val match3 = day1.match3(target, input)

        assertEquals(241861950, match3, "Example should pass")
    }
}