package com.bloidonia.advent2020

import com.bloidonia.linesFromResource

enum class Opcode {
    NOP,
    ACC,
    JMP
}

data class Operation(val op: Opcode, val arg: Int)

class Day_08(source: List<String>) {
    var pc = 0
    var accumulator = 0

    val stack = mutableSetOf<Int>()
    val code = source.map { parse(it) }

    fun parse(line: String): Operation = line.split(" ").let {
        Operation(Opcode.valueOf(it[0].toUpperCase()), it[1].toInt())
    }

    fun reset() {
        pc = 0
        stack.clear()
        accumulator = 0
    }

    fun run(program: List<Operation> = code): Pair<Boolean, Int> {
        reset()
        while (!stack.contains(pc) && pc != program.size) {
            // Keep an eye out for infinite loops
            stack.add(pc)

            when (program[pc].op) {
                Opcode.NOP -> pc += 1
                Opcode.ACC -> {
                    accumulator += program[pc].arg
                    pc += 1
                }
                Opcode.JMP -> pc += program[pc].arg
            }
        }
        return (pc == program.size) to accumulator
    }

    fun bruteForce(): Int {
        // Get all jmp or nop instructions
        code.forEachIndexed { idx, op ->
            if (op.op in listOf(Opcode.JMP, Opcode.NOP)) {
                // Copy the code
                val newCode = code.toMutableList()

                // Swap the instruction at idx
                newCode[idx] = Operation(if (op.op == Opcode.JMP) Opcode.NOP else Opcode.JMP, op.arg)

                // Run it, and see if it stopped because PC went past the last element
                run(newCode).let {
                    if (it.first) {
                        println("Solution was to swap $op at $idx")
                        return it.second
                    }
                }
            }
        }
        // Nothing found
        return -1
    }

}

fun main() {
    val day08 = Day_08(linesFromResource("/8.txt"))

    println(day08.run().second)
    println(day08.bruteForce())
}