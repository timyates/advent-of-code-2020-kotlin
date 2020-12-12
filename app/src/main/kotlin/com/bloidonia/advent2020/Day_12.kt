package com.bloidonia.advent2020

import com.bloidonia.linesFromResource
import java.lang.IllegalArgumentException
import kotlin.math.absoluteValue

data class Position(val x: Int, val y: Int) {

    operator fun plus(other: Position): Position = Position(x + other.x, y + other.y)

    fun manhattan(): Int = x.absoluteValue + y.absoluteValue

    operator fun times(amount: Int): Position = Position(x * amount, y * amount)

}

enum class Direction(private val angle: Int) {
    N(0),
    E(90),
    S(180),
    W(270);

    fun right(amount: Int): Direction {
        return (this.angle + amount).rem(360).let {
            var new = it
            while (new < 0) new += 360
            values().find { it.angle == new }!!
        }
    }

    fun left(amount: Int): Direction {
        return (this.angle - amount).rem(360).let {
            var new = it
            while (new < 0) new += 360
            values().find { it.angle == new }!!
        }
    }
}

data class Waypoint(val position: Position) {

    fun right(amount: Int): Waypoint {
        return when (amount.rem(360)) {
            90 -> Waypoint(Position(position.y, -position.x))
            180 -> Waypoint(Position(-position.x, -position.y))
            270 -> Waypoint(Position(-position.y, position.x))
            else -> this
        }
    }

    fun left(amount: Int): Waypoint {
        return when (amount.rem(360)) {
            90 -> Waypoint(Position(-position.y, position.x))
            180 -> Waypoint(Position(-position.x, -position.y))
            270 -> Waypoint(Position(position.y, -position.x))
            else -> this
        }
    }
}

data class Ship(val position: Position = Position(0, 0), val facing: Direction = Direction.E) {

    fun forward(amount: Int): Ship {
        return when (facing) {
            Direction.N -> north(amount)
            Direction.E -> east(amount)
            Direction.S -> south(amount)
            Direction.W -> west(amount)
        }
    }

    fun north(amount: Int): Ship = Ship(position + Position(0, amount), facing)
    fun south(amount: Int): Ship = Ship(position + Position(0, -amount), facing)
    fun east(amount: Int): Ship = Ship(position + Position(amount, 0), facing)
    fun west(amount: Int): Ship = Ship(position + Position(-amount, 0), facing)
}

data class ShipAndWaypoint(val ship: Ship = Ship(), val waypoint: Waypoint = Waypoint(Position(10, 1))) {

    fun forward(amount: Int): ShipAndWaypoint =
        ShipAndWaypoint(Ship(ship.position + (waypoint.position * amount)), waypoint)

    fun north(amount: Int): ShipAndWaypoint = ShipAndWaypoint(ship, Waypoint(waypoint.position + Position(0, amount)))
    fun south(amount: Int): ShipAndWaypoint = ShipAndWaypoint(ship, Waypoint(waypoint.position + Position(0, -amount)))
    fun east(amount: Int): ShipAndWaypoint = ShipAndWaypoint(ship, Waypoint(waypoint.position + Position(amount, 0)))
    fun west(amount: Int): ShipAndWaypoint = ShipAndWaypoint(ship, Waypoint(waypoint.position + Position(-amount, 0)))

    override fun toString(): String = "Ship at ${ship.position}, waypoint ${waypoint.position}"
}

class Day_12 {

    private fun parseInstruction(command: String): Pair<String, Int> =
        "(\\S)(\\d+)".toRegex().matchEntire(command)!!.let {
            Pair(it.groupValues[1], it.groupValues[2].toInt())
        }

    fun move(current: Ship, command: String): Ship {
        val instruction = parseInstruction(command)
        return when (instruction.first) {
            "N" -> current.north(instruction.second)
            "E" -> current.east(instruction.second)
            "S" -> current.south(instruction.second)
            "W" -> current.west(instruction.second)
            "R" -> Ship(current.position, current.facing.right(instruction.second))
            "L" -> Ship(current.position, current.facing.left(instruction.second))
            "F" -> current.forward(instruction.second)
            else -> throw IllegalArgumentException("Unparseable command $command")
        }
    }

    fun move(current: ShipAndWaypoint, command: String): ShipAndWaypoint {
        val instruction = parseInstruction(command)
        return when (instruction.first) {
            "N" -> current.north(instruction.second)
            "E" -> current.east(instruction.second)
            "S" -> current.south(instruction.second)
            "W" -> current.west(instruction.second)
            "R" -> ShipAndWaypoint(current.ship, current.waypoint.right(instruction.second))
            "L" -> ShipAndWaypoint(current.ship, current.waypoint.left(instruction.second))
            "F" -> current.forward(instruction.second)
            else -> throw IllegalArgumentException("Unparseable command $command")
        }
    }
}

fun main() {
    val day12 = Day_12()
    val part1 = linesFromResource("/12.txt").fold(Ship()) { current, command ->
        day12.move(
            current,
            command
        )
    }.position.manhattan()
    println(part1)

    val part2 = linesFromResource("/12.txt").fold(ShipAndWaypoint()) { current, command ->
        day12.move(
            current,
            command
        )
    }.ship.position.manhattan()
    println(part2)

}