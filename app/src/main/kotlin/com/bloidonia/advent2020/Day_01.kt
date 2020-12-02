package com.bloidonia.advent2020

import com.bloidonia.Utils

class Day_01 {

    fun match2(expected: Int, input: List<Int>) : Int {
        for (a in input) for (b in input) if (a + b == expected) return a * b;
        return -1;
    }

    fun match3(expected: Int, input: List<Int>) : Int {
        for (a in input) for (b in input) for (c in input) if (a + b + c == expected) return a * b * c;
        return -1;
    }

}

fun main() {
    println(Day_01().match2(2020, Utils().intsFromResource("/1.txt")))
    println(Day_01().match3(2020, Utils().intsFromResource("/1.txt")))
}
