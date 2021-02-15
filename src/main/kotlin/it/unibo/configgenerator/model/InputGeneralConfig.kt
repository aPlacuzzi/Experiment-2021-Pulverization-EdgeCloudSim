package it.unibo.configgenerator.model

import com.uchuhimo.konf.ConfigSpec

data class InputGeneralConfig (
    val simulationTime: List<Int> = listOf(30),
    val warmUpPeriod: List<Int> = listOf(3),
    val vmLoadCheckInterval: List<Double> = listOf(0.1),
    val locationCheckInterval: List<Double> = listOf(0.1),
    val fileLogEnabled: Boolean = true,
    val deepFileLogEnabled: Boolean = false,

    val minNumberOfMobileDevices: Int = 100,
    val maxNumberOfMobileDevices: Int = 1000,
    val mobileDeviceCounterSize: Int = 100,

    val wanPropogationDelay: List<Double> = listOf(0.15),
    val gsmPropogationDelay: List<Double> = listOf(0.16),
    val lanInternalDelay: List<Double> = listOf(0.01),
    val wlanBandwidth: List<Int> = listOf(200),
    val wanBandwidth: List<Int> = listOf(15),
    val gsmBandwidth: List<Int> = listOf(10),

    val numberOfHostOnCloudDatacenter: List<Int> = listOf(1),
    val numberOfVmOnCloudHost: List<Int> = listOf(4),
    val coreForCloudVm: List<Int> = listOf(4),
    val mipsForCloudVm: List<Int> = listOf(1_000),
    val ramForCloudVm: List<Int> = listOf(32_000),
    val storageForCloudVm: List<Int> = listOf(1_000_000),

    val coreForMobileVm: List<Int> = listOf(1),
    val mipsForMobileVm: List<Int> = listOf(1_000),
    val ramForMobileVm: List<Int> = listOf(1_800),
    val storageForMobileVm: List<Int> = listOf(32_000),

    //use ',' for multiple values
    val orchestratorPolicies: String = "NEXT_FIT",

    //#use ',' for multiple values
    val simulationScenarios: String = "SINGLE_TIER,TWO_TIER,TWO_TIER_WITH_EO",

    //#mean waiting time in seconds
    val attractivenessL1MeanWaitingTime: List<Int> = listOf(480),
    val attractivenessL2MeanWaitingTime: List<Int> = listOf(300),
    val attractivenessL3MeanWaitingTime: List<Int> = listOf(120),
        ) {
    companion object : ConfigSpec("") {
        val generalConfig by required<InputGeneralConfig>()
    }
}