package com.bloidonia.advent2020

import com.bloidonia.linesFromResource

class Day_13(val now: Long, val busses: String) {

    companion object {
        // Lowest common multiple calculations (thanks to Google)
        fun gcd(x: Long, y: Long): Long = if (y == 0L) x else gcd(y, x % y)
        fun lcm(x: Long, y: Long): Long = x * (y / gcd(x, y))
        fun lcm(input: List<Long>): Long = input.fold(1, this::lcm)
    }

    fun part1(): Long {
        val busNumbers = busses.split(",").filter { it != "x" }.map { it.toLong() }
        for (curr in now until now + busNumbers.maxOrNull()!!) {
            val find = busNumbers.find { curr.rem(it) == 0L }
            if (find != null) return (curr - now) * find
        }
        return -1;
    }

    fun part2(): Long {
        // Get list of bus pairs, where `first` is the bus line (time), and `second` is the offset due to the position
        // of the bus
        val busNumbers = busses.split(",")
            .mapIndexed { index, offset -> if (offset == "x") null else Pair(offset.toLong(), index) }
            .filterNotNull()

        val lowestCommonMultiple = lcm(busNumbers.map { it.first })

        // Start at the offset of the first bus
        var timeStamp = busNumbers[0].first

        for (i in busNumbers.indices.drop(1)) {
            // we can jump the lcm of the busses up to here (means we keep the previous busses lined up)
            val jump = busNumbers.take(i).fold(1L) { acc, bus -> lcm(acc, bus.first) }

            // Find the point where the next bus lines up (don't forget the positional offset)
            timeStamp = (timeStamp..lowestCommonMultiple step jump)
                .first { (it + busNumbers[i].second) % busNumbers[i].first == 0L }
        }
        return timeStamp
    }
}

fun main() {
    linesFromResource("/13.txt").apply {
        println(Day_13(this[0].toLong(), this[1]).part1())
    }

    linesFromResource("/13.txt").apply {
        println(Day_13(this[0].toLong(), this[1]).part2())
    }
}