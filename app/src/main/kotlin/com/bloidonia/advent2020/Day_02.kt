package com.bloidonia.advent2020

import com.bloidonia.Utils

fun parse(line: String): Rule? = """^(\d+)-(\d+) (\S{1}): (\S+)$""".toRegex().matchEntire(line)?.let {
    Rule(it.groups[1]?.value?.toInt()!!, it.groups[2]?.value?.toInt()!!, it.groups[3]?.value!!, it.groups[4]?.value!!)
}

data class Rule(val min: Int, val max: Int, val letter: String, val password: String) {

    fun matches(): Boolean = password.count { it == letter[0] }.let {
        it >= min && it <= max
    }

    fun matches2(): Boolean = (password[min - 1] == letter[0]).xor(password[max - 1] == letter[0])
}

fun main() {
    println(Utils().linesFromResource("/2.txt").map { parse(it)?.matches2() ?: false }.count { it })
}