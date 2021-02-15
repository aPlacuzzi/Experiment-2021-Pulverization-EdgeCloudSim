package it.unibo.configgenerator.model

import com.uchuhimo.konf.ConfigSpec

data class InputEdgesConfig (
    val costPerBw: Double = 0.1,
    val costPerSec: Double = 3.0,
    val costPerMem: Double = 0.05,
    val costPerStorage: Double = 0.1,
    val edgeCount: Int = 1,
    val edgeCore: Int = 4,
    val edgeMips: Int = 20_000,
    val edgeRam: Int = 16_000,
    val edgeStorage: Int = 250_000,
    val edgeVwCount: Int = 4,
    val locations: List<InputLocation> = listOf(InputLocation()),
        ) {
    companion object : ConfigSpec("") {
        val edgesConfig by required<InputEdgesConfig>()
    }
}

data class InputLocation (
    val x: Int = 1,
    val y: Int = 1,
    val attractiveness: Int = 0,
        ) {
    companion object : ConfigSpec("") {
        val locationConfig by required<InputLocation>()
    }
}