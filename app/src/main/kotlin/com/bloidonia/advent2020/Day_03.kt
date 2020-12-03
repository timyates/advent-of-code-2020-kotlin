package com.bloidonia.advent2020

import com.bloidonia.linesFromResource

class Day_03(val treeLines: List<String>) {

    data class Position(val x: Int, val y: Int)

    /**
     * works out a sequence of "y" positions for the speed we are moving "down" the mountain
     *
     * starts at 0 and ends at the last point before the height of the mountain
     */
    private fun yPositions(stepsDown: Int = 1): Sequence<Int> =
        generateSequence(0) { it + stepsDown }.takeWhile { it < treeLines.size }

    /**
     * works out some x,y locations we need to look for trees
     */
    fun positions(stepsRight: Int = 3, stepsDown: Int = 1): Sequence<Position> =
        yPositions(stepsDown).mapIndexed { index, yPosition ->
            Position(
                index * stepsRight % treeLines[yPosition].length,
                yPosition
            )
        }

    /**
     * for each x,y position count the number of times we hit a tree
     *
     * return it as a long, as we multiply a load together and quickly run out of room in an int and go negative as a result
     */
    fun treeCount(right: Int = 3, down: Int = 1): Long =
        positions(right, down).filter { p -> treeLines[p.y][p.x] == '#' }.count().toLong()

}

fun main() {
    val lines = linesFromResource("/3.txt")
    val day03 = Day_03(lines)

    println(day03.treeCount())

    println(
        day03.treeCount(1, 1) *
                day03.treeCount(3, 1) *
                day03.treeCount(5, 1) *
                day03.treeCount(7, 1) *
                day03.treeCount(1, 2)
    )
}
