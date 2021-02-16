package it.unibo.configgenerator.model

import com.uchuhimo.konf.ConfigSpec

data class InputDeployConfig(
    val sensingUploadData: Double = 0.0,
    val actuatorDownloadData: Double = 0.0,
    val behaviour: InputApplication = InputApplication("behaviour"),
    val communication: InputApplication = InputApplication("communication")) {
    companion object: ConfigSpec("") {
        val inputDeployConfig by required<InputDeployConfig>()
    }
}

data class InputApplication(
    val name: String = "empty",
    val usagePercentage: List<Double> = listOf(20.0),
    val probCloudSelection: List<Double> = listOf(20.0),
    val poissonInterarrival: List<Double> = listOf(20.0),
    val delaySensitivity: List<Double> = listOf(0.0),
    val activePeriod: List<Double> = listOf(20.0),
    val idlePeriod: List<Double> = listOf(0.0),
    val dataUpload: List<Double> = listOf(2500.0),
    val dataDownload:List<Double> = listOf(250.0),
    val taskLength: List<Double> = listOf(3000.0),
    val requiredCore: List<Int> = listOf(1),
    val vmUtilizationOnEdge: List<Double> = listOf(30.0),
    val vmUtilizationOnCloud: List<Double> = listOf(3.0),
    val vmUtilizationOnMobile: List<Double> = listOf(0.0),
)