package com.bloidonia.advent2020

import com.bloidonia.linesFromResource

class Day_16 {

    data class CategoryList(val categories: List<Category>) {
        fun valid(num: Int) = sequence {
            categories.forEach { c ->
                if (c.valid(num)) {
                    yield(c);
                }
            }
        }

        fun simplyInvalid(num: Int) = valid(num).toList().isEmpty()
    }

    data class Category(val name: String, val low: IntRange, val high: IntRange) {
        fun valid(num: Int): Boolean = low.contains(num) || high.contains(num)
    }

    data class Ticket(val values: List<Int>) {
        companion object {
            fun parse(line: String) = Ticket(line.split(",").map { it.toInt() })
        }

        fun simpleErrors(categories: CategoryList) = values.filter { categories.simplyInvalid(it) }.sum()
    }

    fun parseCategory(line: String) =
        "([^:]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)".toRegex().matchEntire(line)?.let {
            Category(
                it.groupValues[1],
                it.groupValues[2].toInt()..it.groupValues[3].toInt(),
                it.groupValues[4].toInt()..it.groupValues[5].toInt()
            )
        }!!

    fun parseFile(lines: List<String>) {
        val categories = CategoryList(lines.takeWhile { it.isNotEmpty() }.map { parseCategory(it) })
        val myTicket = Ticket.parse(lines.dropWhile { it.isNotEmpty() }.drop(2).take(1)[0])
        val nearTickets = lines.dropWhile { it.isNotEmpty() }.drop(5).map { Ticket.parse(it) }

        println(categories)
        println(myTicket)
        println(nearTickets)
        println(nearTickets.map { it.simpleErrors(categories) }.sum())
    }
}

fun main() {
    Day_16().parseFile(linesFromResource("/16.txt"))
}