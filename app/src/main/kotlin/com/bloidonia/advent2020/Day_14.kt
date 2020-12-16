package com.bloidonia.advent2020

import com.bloidonia.linesFromResource

class Day_14 {

    class Masks(mask: String = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX") {
        val ones = mask.map { it == '1' }.reversed()
            .foldIndexed(0L) { idx, acc, on -> acc.or((if (on) 1L else 0L).shl(idx)) }
        val zeros = mask.map { it == '0' }.reversed()
            .foldIndexed(Long.MAX_VALUE) { idx, acc, on -> acc.xor((if (on) 1L else 0L).shl(idx)) }

        fun process(number: Long) = number.or(ones).and(zeros)
    }

    data class Cpu(val memory: Map<Int, Long> = mapOf(), val mask: Masks = Masks()) {
        fun apply(location: Int, value: Long) = Cpu(memory.plus(location to mask.process(value)), mask)
        fun mask(mask: String) = Cpu(memory, Masks(mask))
    }

    fun part1(lines: List<String>): Long {
        val maskRegex = "mask = ([01X]+)".toRegex()
        val assignRegex = "mem\\[(\\d+)\\] = (\\d+)".toRegex()
        val endState = lines.fold(Cpu()) { cpu, line ->
            if (maskRegex.matches(line)) {
                maskRegex.matchEntire(line).let {
                    return@fold cpu.mask(it!!.groupValues[1])
                }
            } else if (assignRegex.matches(line)) {
                assignRegex.matchEntire(line).let {
                    return@fold cpu.apply(it!!.groupValues[1].toInt(), it!!.groupValues[2].toLong())
                }
            } else {
                cpu
            }
        }
        return endState.memory.values.sum()
    }
}

fun main() {
    println(Day_14().part1(linesFromResource("/14.txt")))
}