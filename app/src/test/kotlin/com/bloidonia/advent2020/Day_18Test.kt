package com.bloidonia.advent2020

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals as assertEquals1

class Day_18Test {

    @Test
    fun `part 1 examples`() {
        assertEquals1(51, process("1 + (2 * 3) + (4 * (5 + 6))"))
        assertEquals1(26, process("2 * 3 + (4 * 5)"))
        assertEquals1(437, process("5 + (8 * 3 + 9 + 3 * 4 * 3)"))
        assertEquals1(12240, process("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"))
        assertEquals1(13632, process("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"))
    }

    @Test
    fun `part 2 examples`() {
        assertEquals1(51, process("1 + (2 * 3) + (4 * (5 + 6))", true))
        assertEquals1(46, process("2 * 3 + (4 * 5)", true))
        assertEquals1(1445, process("5 + (8 * 3 + 9 + 3 * 4 * 3)", true))
        assertEquals1(669060, process("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", true))
        assertEquals1(23340, process("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", true))
    }

    private fun process(s: String, plusPrecidence: Boolean = false): Long {
        return Day_18().let {
            it.process(it.tokenize(s).toList(), plusPrecidence)
        }
    }
}