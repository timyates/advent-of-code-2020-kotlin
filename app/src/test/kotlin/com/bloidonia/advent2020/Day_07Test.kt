package com.bloidonia.advent2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day_07Test {

    val example = """light red bags contain 1 bright white bag, 2 muted yellow bags.
                    |dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                    |bright white bags contain 1 shiny gold bag.
                    |muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                    |shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                    |dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                    |vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                    |faded blue bags contain no other bags.
                    |dotted black bags contain no other bags.""".trimMargin()

    val test = Day_07(example.split("\n"))

    @Test
    fun `can tokenize`() {
        var tokens = test.tokenize("light red bags contain 1 bright white bag, 2 muted yellow bags.")
        assertEquals(listOf("light red", "1 bright white", "2 muted yellow"), tokens)

        tokens = test.tokenize("faded blue bags contain no other bags.")
        assertEquals(listOf("faded blue"), tokens)
    }

    @Test
    fun `can pair`() {
        var entry = test.toEntry("light red bags contain 1 bright white bag, 2 muted yellow bags.")
        assertEquals("light red" to listOf(Day_07.Bag(1, "bright white"), Day_07.Bag(2, "muted yellow")), entry)

        entry = test.toEntry("faded blue bags contain no other bags.")
        assertEquals("faded blue" to listOf<Day_07.Bag>(), entry)
    }

    @Test
    fun `example is as expected`() {
        val bagsForColor = test.walkColorsBackwards("shiny gold")

        assertEquals(setOf("bright white", "muted yellow", "dark orange", "light red"), bagsForColor)
    }

    @Test
    fun `example part 2 is as expected`() {
        val totalBags = test.bagCount("shiny gold")

        assertEquals(32, totalBags)
    }

}