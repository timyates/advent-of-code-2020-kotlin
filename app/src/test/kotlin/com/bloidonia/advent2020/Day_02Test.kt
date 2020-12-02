package com.bloidonia.advent2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day_02Test {

    val line1 = parse("1-3 a: abcde")!!
    val line2 = parse("1-3 b: cdefg")!!
    val line3 = parse("2-9 c: ccccccccc")!!

    @Test
    fun `can parse an example line`() {
        val expected = Rule(1, 3, "a", "abcde")
        assertEquals(expected, line1, "Rule should be parsed")
    }

    @Test
    fun `lines pass and fail as per the examples of part 1`() {
        assert(line1.matches()) { "Line 1 should match" }
        assert(!line2.matches()) { "Line 2 should not match" }
        assert(line3.matches()) { "Line 3 should match" }
    }

    @Test
    fun `lines pass and fail as per the examples of part 2`() {
        assert(line1.matches2()) { "Line 1 should match" }
        assert(!line2.matches2()) { "Line 2 should not match" }
        assert(!line3.matches2()) { "Line 3 should not match" }
    }
}