package com.bloidonia.advent2020

import com.bloidonia.intsFromResource

class Day_10(val ratings: List<Int>) {

    val sorted = ratings.sorted()

    // We can't skip adaptors for part 1 so it's just the list of differences
    fun differences() = sequence {
        sorted.apply {
            yield(this[0])
            for (i in 0 until size - 1) {
                yield(this[i + 1] - this[i])
            }
            yield(3) // for the device at the end
        }
    }

    // Needs to be a long, we wrap an integer
    fun combinations(): Long? {
        // Always 1 route, so seed the switch with 1 path
        val seed = mapOf(0 to 1L)

        // Starting with the seed map, iterate through the list, and fold the results into the map
        val map = sorted.fold(seed) { map, element ->

            // Look up total number of routes to all voltages within range of this one
            val choices = (element - 3 until element).map { map[it] ?: 0 }.sum()

            // Then store the routes to this voltage
            map.plus(element to choices)
        }
        // And return the final total
        return map[sorted.last()]
    }
}

fun main() {
    val test = Day_10(intsFromResource("/10.txt"))
    val part1 = test.differences().toList()

    println(part1.count { it == 1 } * part1.count { it == 3 })

    println(test.combinations())
}