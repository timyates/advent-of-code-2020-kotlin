package com.bloidonia

// placeholder for getResource
interface Utils

fun textFromResource(name: String) = Utils::class.java.getResource(name).readText()
fun linesFromResource(name: String) = textFromResource(name).split("\n")
fun intsFromResource(name: String) = linesFromResource(name).map { it.toInt() }
