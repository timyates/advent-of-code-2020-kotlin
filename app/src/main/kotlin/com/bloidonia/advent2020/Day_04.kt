package com.bloidonia.advent2020

import com.bloidonia.textFromResource

class Day_04(batch: String) {

    val entries = batch.split("\n\n")                                      // entries separated by 2 lines
        .map { it.split("\n").joinToString(" ") }                          // then get each entry onto a single line
        .map { "([^ :]+):([^ ]+)".toRegex().findAll(it) }                  // find all the key:value pairs
        .map { it.map { it.groupValues[1] to it.groupValues[2] }.toMap() } // and make them into a map per passport

    fun valid4DigitRange(lower: Int, upper: Int) = { s: String -> s.length == 4 && s.toInt() in lower..upper }

    val validators = mapOf(
        "cid" to { _ -> true },
        "byr" to valid4DigitRange(1920, 2002),
        "iyr" to valid4DigitRange(2010, 2020),
        "eyr" to valid4DigitRange(2020, 2030),
        "hgt" to { s ->
            "^(\\d{2,3})(cm|in)$".toRegex().matchEntire(s)?.let {
                if (it.groupValues[2] == "cm") {
                    it.groupValues[1].toInt() in 150..193
                } else {
                    it.groupValues[1].toInt() in 59..76
                }
            } == true
        },
        "hcl" to { s -> "^#[0-9a-f]{6}$".toRegex().matches(s) },
        "ecl" to { s -> setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(s) },
        "pid" to { s -> "^[0-9]{9}$".toRegex().matches(s) }
    )


    private fun validPart1() = entries.filter {
        it.keys.containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))
    }

    fun countValidPart1() = validPart1().size

    fun countValidPart2() = validPart1().filter { entry -> entry.all { validators[it.key]?.invoke(it.value) ?: false } }.size
}

fun main() {
    val text = textFromResource("/4.txt")
    val day04 = Day_04(text)

    println(day04.countValidPart1())

    println(day04.countValidPart2())
}
