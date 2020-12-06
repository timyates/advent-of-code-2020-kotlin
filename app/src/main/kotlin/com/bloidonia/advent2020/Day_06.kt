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
            it.split("\n").joinToString("").toSet().size
        }
        .sum()
    println(part1)

    val part2 = groupData
        .map {
            it.split("\n").map { it.toSet() }.reduceRight { s, g -> s.intersect(g) }.size
        }
        .sum()

    println(part2)
}