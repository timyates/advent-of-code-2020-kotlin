package com.bloidonia

// placeholder for getResource
interface Utils

fun linesFromResource(name: String) : List<String> = Utils::class.java.getResource(name).readText().split("\n")

fun intsFromResource(name: String) : List<Int> = linesFromResource(name).map { it.toInt() }
