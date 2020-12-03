package com.bloidonia.advent2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day_03Test {

    val treeLines = """..##.......
                      |#...#...#..
                      |.#....#..#.
                      |..#.#...#.#
                      |.#...##..#.
                      |..#.##.....
                      |.#.#.#....#
                      |.#........#
                      |#.##...#...
                      |#...##....#
                      |.#..#...#.#""".trimMargin().split("\n")
    val test = Day_03(treeLines)

    @Test
    fun `can generate the indices for input`() {
        assertEquals(
            listOf(0, 3, 6, 9, 1, 4, 7, 10, 2, 5, 8),
            test.positions().map { it.x }.toList()
        )
    }

    @Test
    fun `correct for example`() {
        assertEquals(7, test.treeCount())
    }

    @Test
    fun `correct for part2 example`() {
        assertEquals(2, test.treeCount(1, 1))
        assertEquals(7, test.treeCount(3, 1))
        assertEquals(3, test.treeCount(5, 1))
        assertEquals(4, test.treeCount(7, 1))
        assertEquals(2, test.treeCount(1, 2))
    }

}