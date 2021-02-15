package it.unibo.configgenerator.model

import com.google.common.collect.Lists
import com.uchuhimo.konf.ConfigSpec
import kotlinx.serialization.Serializable

@Serializable
data class OutputGeneralConfig(
    val simulation_time: Int,
    val warm_up_period: Int,
    val vm_load_check_interval: Double,
    val location_check_interval: Double,
    val file_log_enabled: Boolean,
    val deep_file_log_enabled: Boolean,

    val min_number_of_mobile_devices: Int,
    val max_number_of_mobile_devices: Int,
    val mobile_device_counter_size: Int,

    val wan_propogation_delay: Double,
    val gsm_propogation_delay: Double,
    val lan_internal_delay: Double,
    val wlan_bandwidth: Int,
    val wan_bandwidth: Int,
    val gsm_bandwidth: Int,

    val number_of_host_on_cloud_datacenter: Int,
    val number_of_vm_on_cloud_host: Int,
    val core_for_cloud_vm: Int,
    val mips_for_cloud_vm: Int,
    val ram_for_cloud_vm: Int,
    val storage_for_cloud_vm: Int,

    val core_for_mobile_vm: Int,
    val mips_for_mobile_vm: Int,
    val ram_for_mobile_vm: Int,
    val storage_for_mobile_vm: Int,

    //use ',' for multiple values
    val orchestrator_policies: String = "NEXT_FIT",

    //#use ',' for multiple values
    val simulation_scenarios: String = "SINGLE_TIER,TWO_TIER,TWO_TIER_WITH_EO",

    //#mean waiting time in seconds
    val attractiveness_L1_mean_waiting_time: Int,
    val attractiveness_L2_mean_waiting_time: Int,
    val attractiveness_L3_mean_waiting_time: Int,
        ){
    companion object :ConfigSpec("") {
        val outputGeneralConfig by required<OutputGeneralConfig>()

        fun fromInputModel(inputGeneralConfig: InputGeneralConfig): List<OutputGeneralConfig> {
            val list = listOf(
                inputGeneralConfig.simulationTime,
                inputGeneralConfig.warmUpPeriod,
                inputGeneralConfig.vmLoadCheckInterval,
                inputGeneralConfig.locationCheckInterval,
                inputGeneralConfig.wanPropogationDelay,
                inputGeneralConfig.gsmPropogationDelay,
                inputGeneralConfig.lanInternalDelay,
                inputGeneralConfig.wlanBandwidth,
                inputGeneralConfig.wanBandwidth,
                inputGeneralConfig.gsmBandwidth,
                inputGeneralConfig.numberOfHostOnCloudDatacenter,
                inputGeneralConfig.numberOfVmOnCloudHost,
                inputGeneralConfig.coreForCloudVm,
                inputGeneralConfig.mipsForCloudVm,
                inputGeneralConfig.ramForCloudVm,
                inputGeneralConfig.storageForCloudVm,
                inputGeneralConfig.coreForMobileVm,
                inputGeneralConfig.mipsForMobileVm,
                inputGeneralConfig.ramForMobileVm,
                inputGeneralConfig.storageForMobileVm,
                inputGeneralConfig.attractivenessL1MeanWaitingTime,
                inputGeneralConfig.attractivenessL2MeanWaitingTime,
                inputGeneralConfig.attractivenessL3MeanWaitingTime,
            )
            return Lists.cartesianProduct(list).map {
                OutputGeneralConfig(
                     simulation_time = it[0].toInt(),
                     warm_up_period = it[1].toInt(),
                     vm_load_check_interval = it[2].toDouble(),
                     location_check_interval = it[3].toDouble(),
                     file_log_enabled = inputGeneralConfig.fileLogEnabled,
                     deep_file_log_enabled = inputGeneralConfig.deepFileLogEnabled,
                     min_number_of_mobile_devices = inputGeneralConfig.minNumberOfMobileDevices,
                     max_number_of_mobile_devices = inputGeneralConfig.maxNumberOfMobileDevices,
                     mobile_device_counter_size = inputGeneralConfig.mobileDeviceCounterSize,
                     wan_propogation_delay = it[4].toDouble(),
                     gsm_propogation_delay = it[5].toDouble(),
                     lan_internal_delay = it[6].toDouble(),
                     wlan_bandwidth = it[7].toInt(),
                     wan_bandwidth = it[8].toInt(),
                     gsm_bandwidth = it[9].toInt(),
                     number_of_host_on_cloud_datacenter = it[10].toInt(),
                     number_of_vm_on_cloud_host = it[11].toInt(),
                     core_for_cloud_vm = it[12].toInt(),
                     mips_for_cloud_vm = it[13].toInt(),
                     ram_for_cloud_vm = it[14].toInt(),
                     storage_for_cloud_vm = it[15].toInt(),
                     core_for_mobile_vm = it[16].toInt(),
                     mips_for_mobile_vm = it[17].toInt(),
                     ram_for_mobile_vm = it[18].toInt(),
                     storage_for_mobile_vm = it[19].toInt(),
                     orchestrator_policies = inputGeneralConfig.orchestratorPolicies,
                     simulation_scenarios = inputGeneralConfig.simulationScenarios,
                     attractiveness_L1_mean_waiting_time = it[20].toInt(),
                     attractiveness_L2_mean_waiting_time = it[21].toInt(),
                     attractiveness_L3_mean_waiting_time = it[22].toInt(),
                )
            }
        }
    }
}