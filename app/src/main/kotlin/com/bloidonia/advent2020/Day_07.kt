package com.bloidonia.advent2020

import com.bloidonia.linesFromResource

class Day_07(input: List<String>) {

    data class Bag(val quantity: Int, val color: String)

    // Split the sentences into bag colours and numbers
    fun tokenize(input: String) =
        input.split("bag[s]?|contain|,|\\.|no other".toRegex()).map { it.trim() }.filter(String::isNotEmpty)

    // Given a string generate a map entry with a colour key, and a list of bags and quantities as values
    fun toEntry(input: String): Pair<String, List<Bag>> {
        val tokens = tokenize(input)
        return tokens[0] to tokens.drop(1).map { toBag(it) }
    }

    // parse a "9 wee yellow" string into a quantity and colour
    private fun toBag(numberAndColor: String): Bag {
        return "(\\d+) (.+)".toRegex().matchEntire(numberAndColor)!!.groupValues.let {
            Bag(it[1].toInt(), it[2])
        }
    }

    // Take the string of rules, and build a big map from it
    private val bagMap = input.map { toEntry(it) }.toMap()

    // look through the map, and return a list of the colours of all bags that contain bags of the required color
    private fun colourOfBagsContaining(color: String) =
        bagMap.filter { bagEntry -> bagEntry.value.map { it.color }.contains(color) }.map { it.key }

    // starting at a color, find all bags that contain it.  Then find all bags that contain them.  Keep going till we've not more bags to look for
    fun walkColorsBackwards(color: String): Set<String> {
        val result = mutableSetOf<String>()
        colourOfBagsContaining(color).let { bagNames ->
            result.addAll(bagNames)
            var queueToCheck = bagNames
            while (queueToCheck.isNotEmpty()) {
                queueToCheck = queueToCheck.map(this::colourOfBagsContaining).flatten().filter { it !in result }
                result.addAll(queueToCheck)
            }
            return result
        }
    }

    // given a bag color, recursively look how many bags are inside it
    fun bagCount(color: String): Int =
        bagMap[color]!!.map { it.quantity + (it.quantity * bagCount(it.color)) }.sum()
}

fun main() {
    val day07 = Day_07(linesFromResource("/7.txt"))

    val result = day07.walkColorsBackwards("shiny gold")

    println(result.size)

    println(day07.bagCount("shiny gold"))
}