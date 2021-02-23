@file:JvmName("Main")
package it.unibo.configgenerator.main

import it.unibo.configgenerator.controller.ConfigGenerator
import it.unibo.configgenerator.controller.ProtelisComplexity
import java.io.File

fun main(args: Array<String>) {
    val protelisComplexity = ProtelisComplexity(args[2])
    ConfigGenerator.generateConfig(File(args[0]), args[1], protelisComplexity)
}