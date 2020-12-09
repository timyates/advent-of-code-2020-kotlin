package com.bloidonia

// placeholder for getResource
interface Utils

fun textFromResource(name: String) = Utils::class.java.getResource(name).readText()
fun linesFromResource(name: String) = textFromResource(name).split("\n")
fun <T> linesFromResource(name: String, mapper: (String) -> T) = linesFromResource(name).map(mapper)

fun intsFromResource(name: String) = linesFromResource(name) { s -> s.toInt() }
fun longsFromResource(name: String) = linesFromResource(name) { s -> s.toLong() }
