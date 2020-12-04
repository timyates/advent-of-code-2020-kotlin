package com.bloidonia.advent2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day_04Test {

    val batch = """ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
                  |byr:1937 iyr:2017 cid:147 hgt:183cm
                  |
                  |iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
                  |hcl:#cfa07d byr:1929
                  |
                  |hcl:#ae17e1 iyr:2013
                  |eyr:2024
                  |ecl:brn pid:760753108 byr:1931
                  |hgt:179cm
                  |
                  |hcl:#cfa07d eyr:2025 pid:166559648
                  |iyr:2011 ecl:brn hgt:59in""".trimMargin()

    val test = Day_04(batch)

    @Test
    fun `parses entries correctly`() {
        assertEquals(4, test.entries.size)
    }

    @Test
    fun `part 1 validity works`() {
        assertEquals(2, test.countValidPart1())
    }

    @Test
    fun `four digit validity`() {
        assert(test.valid4DigitRange(10, 20).invoke("0015"))
        assert(!test.valid4DigitRange(10, 20).invoke("0021"))
        assert(!test.valid4DigitRange(10, 20).invoke("15"))
    }

    @Test
    fun `height validity`() {
        assert(test.validators["hgt"]?.invoke("60in") ?: false)
        assert(test.validators["hgt"]?.invoke("150cm") ?: false)
        assert(test.validators["hgt"]?.invoke("193cm") ?: false)

        assert(!(test.validators["hgt"]?.invoke("190in") ?: false))
        assert(!(test.validators["hgt"]?.invoke("190") ?: false))
    }
}