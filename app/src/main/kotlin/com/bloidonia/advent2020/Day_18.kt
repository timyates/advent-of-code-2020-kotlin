package com.bloidonia.advent2020

import com.bloidonia.linesFromResource

class Day_18 {

    enum class Op {
        MUL, DIV, ADD, SUB, OPEN, CLOSE, NUMBER
    }

    data class Token(val op: Op, val arg: Long? = null)

    fun tokenize(input: String) =
        "(\\(|\\d+|\\+|-|\\*|/|\\))".toRegex().findAll(input).let {
            var depth = 0L
            it.map {
                when (it.groupValues[0].trim()) {
                    "*" -> Token(Op.MUL)
                    "/" -> Token(Op.DIV)
                    "+" -> Token(Op.ADD)
                    "-" -> Token(Op.SUB)
                    "(" -> Token(Op.OPEN, ++depth)
                    ")" -> Token(Op.CLOSE, depth--)
                    else -> Token(Op.NUMBER, it.groupValues[0].trim().toLong())
                }
            }
        }

    fun process(input: List<Token>, plusPrecidence: Boolean = false): Long {
        var processing = input
        while (processing.find { it.op == Op.OPEN } != null) {
            val highest = processing.maxOfOrNull { if (it.op == Op.OPEN) it.arg!! else 0 }!!
            val start = processing.indexOfFirst { it.op == Op.OPEN && it.arg == highest }
            val end = processing.indexOfFirst { it.op == Op.CLOSE && it.arg == highest }
            processing = processing.take(start)
                .plusElement(Token(Op.NUMBER, process(processing.subList(start + 1, end), plusPrecidence)))
                .plus(processing.drop(end + 1))
        }
        if (plusPrecidence) {
            while (processing.find { it.op == Op.ADD } != null) {
                val plusIndex = processing.indexOfFirst { it.op == Op.ADD }
                processing = processing.take(plusIndex - 1)
                    .plusElement(Token(Op.NUMBER, processing[plusIndex - 1].arg!! + processing[plusIndex + 1].arg!!))
                    .plus(processing.drop(plusIndex + 2))
            }
        }
        val start = processing.first().arg!!.toLong()
        return processing.drop(1).chunked(2).fold(start) { acc, ops ->
            if (ops[0].op == Op.MUL) acc * ops[1].arg!!
            else if (ops[0].op == Op.DIV) acc / ops[1].arg!!
            else if (ops[0].op == Op.ADD) acc + ops[1].arg!!
            else acc - ops[1].arg!!
        }
    }
}

fun main() {
    val day18 = Day_18()
    val part1 = linesFromResource("/18.txt").map {
        day18.process(day18.tokenize(it).toList(), false).toLong()
    }.sum()

    println(part1)

    val part2 = linesFromResource("/18.txt").map {
        day18.process(day18.tokenize(it).toList(), true).toLong()
    }.sum()

    println(part2)
}