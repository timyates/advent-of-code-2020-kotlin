package com.bloidonia.advent2020

import com.bloidonia.textFromResource
import kotlin.streams.toList

class Day_06 {
}

fun main() {
    val groupData = textFromResource("/6.txt")
        .split("\n\n")

    val part1 = groupData
        .map {
            // For each group, join all the lines together into one long string
            // Get a unique set of chars, and get the size of this set
            it.split("\n").joinToString("").toSet().size
        }
        .sum() // Then add all these sizes together

    println(part1)

    val part2 = groupData
        .map {
            // For each persone in the group, get a set of their answers
            it.split("\n").map { it.toSet() }
                // Then find the intersection of all the answers in a groups
                .reduceRight { s, g -> s.intersect(g) }
                // And get the size
                .size
        }
        .sum() // Then add these sizes together

    println(part2)
}