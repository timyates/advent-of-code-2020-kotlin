package com.bloidonia.advent2020

import com.bloidonia.advent2020.Day_16.Category
import com.bloidonia.advent2020.Day_16.CategoryList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day_16Test {

    @Test
    fun `can parse category`() {
        assertEquals(
            Category("seat", 13..40, 45..50),
            Day_16().parseCategory("seat: 13-40 or 45-50")
        )
    }

    @Test
    fun `can find valid categories`() {
        assertEquals(
            listOf<Category>(),
            CategoryList(
                listOf(
                    Category("row", 6..11, 33..44),
                    Category("seat", 13..40, 45..50)
                )
            ).valid(4).toList()
        )

        assertEquals(
            listOf(Category("row", 6..11, 33..44)),
            CategoryList(
                listOf(
                    Category("row", 6..11, 33..44),
                    Category("seat", 13..40, 45..50)
                )
            ).valid(6).toList()
        )
    }
}