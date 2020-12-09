package com.bloidonia.advent2020

import com.bloidonia.longsFromResource

class Day_09 {

    // Given a list of T, return a sequence of every combination of pairs
    fun <T> combinationPairs(input: List<T>): Sequence<Pair<T, T>> = sequence {
        for (i in 0 until input.size - 1)
            for (j in i + 1 until input.size)
                yield(input[i] to input[j])
    }

    fun part1(input: List<Long>, preamble: Int): Long {
        for (start in 0 until input.size - preamble) {
            input.drop(start).take(preamble + 1).let { chunk ->
                if (combinationPairs(chunk.take(preamble)).none { it.first + it.second == chunk.last() }) {
                    return chunk.last()
                }
            }
        }
        return -1
    }

    fun part2(input: List<Long>, invalidNumber: Long): Long {
        for (idx in input.indices) {
            for (span in idx + 1 until input.size) {
                val block = input.drop(idx).take(span - idx)
                val sum = block.sum()
                if (sum == invalidNumber) {
                    return block.maxOrNull()!! + block.minOrNull()!!
                } else if (sum > invalidNumber) {
                    break
                }
            }
        }
        return -1
    }
}

fun main() {

    val day09 = Day_09()

    val input = longsFromResource("/9.txt")
    val invalidNumber = day09.part1(input, 25)
    println(invalidNumber)

    println(day09.part2(input, invalidNumber))
}