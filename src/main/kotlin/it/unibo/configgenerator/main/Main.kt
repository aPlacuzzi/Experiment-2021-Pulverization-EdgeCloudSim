package it.unibo.configgenerator.main

import it.unibo.configgenerator.controller.ConfigGenerator
import it.unibo.configgenerator.controller.ProtelisComplexity
import java.io.File

fun main(args: Array<String>) {
    val (mips, msgSize) = ProtelisComplexity.estimateProtelisComplexity("nbr(2)")
    ConfigGenerator.generateConfig(File(args[0]), mips, msgSize)
}