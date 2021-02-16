package it.unibo.configgenerator.model

import com.google.common.collect.Lists
import java.math.BigDecimal
import java.math.RoundingMode

data class OutputDeployConfig(val deploy: List<Application>) {
    companion object {
        fun onlyCommunication(
            inputDeployConfig: InputDeployConfig,
            deviceCount: Int,
            apCount: Int,
            messageSize: Double) = allApplication(inputDeployConfig.communication.copy(dataUpload = listOf(0.0), dataDownload = listOf(0.0)))
                .map {
                    val pc = it.prob_cloud_selection / 100.0
                    val pe = 1 - pc
                    val neigh = deviceCount / apCount
                    val dataDownload = (pc * messageSize * pe * neigh) + (pe * (messageSize * (pc * neigh + pe * neigh * (apCount - 1) / apCount)))
                    val dataUpload = if (pc == 1.0) 0.0 else messageSize
                    return@map it.copy(data_download = dataDownload, data_upload = dataUpload)
                }
                .map { it.copy(
                    data_upload = it.data_upload + inputDeployConfig.actuatorDownloadData,
                    data_download = it.data_download + inputDeployConfig.sensingUploadData
                ) }
                .map { OutputDeployConfig(listOf(it)) }

        fun behaviourWithCommunication(
            inputDeployConfig: InputDeployConfig,
            deviceCount: Int,
            apCount: Int,
            mips: Double,
            messageSize: Double
        ) = onlyCommunication(inputDeployConfig, deviceCount, apCount, messageSize)
            .map { OutputDeployConfig(listOf(it.deploy[0].copy(
                name = "behaviour with communication",
                task_length = it.deploy[0].task_length + mips)))
            }

        fun behaviourAndCommunication(
            inputDeployConfig: InputDeployConfig,
            deviceCount: Int,
            apCount: Int,
            mips: Double,
            messageSize: Double
        ): List<OutputDeployConfig> {
            val commToBehaUpload = messageSize * (deviceCount / apCount)
            val commToBehaDownload = messageSize
            val communications = onlyCommunication(inputDeployConfig, deviceCount, apCount, messageSize)
                .map { it.deploy[0].copy(
                    data_upload = it.deploy[0].data_upload + commToBehaUpload,
                    data_download = it.deploy[0].data_download + commToBehaDownload)
                }
            val behaviour = allApplication(inputDeployConfig.behaviour.copy(
                taskLength = listOf(mips),
                dataUpload = listOf(commToBehaDownload),
                dataDownload = listOf(commToBehaUpload)))
            return Lists.cartesianProduct(behaviour, communications).map { OutputDeployConfig(listOf(it[0], it[1])) }
        }

        private fun allApplication(inputApplication: InputApplication): List<Application> {
            val params = listOf(
                inputApplication.usagePercentage,
                inputApplication.probCloudSelection,
                inputApplication.poissonInterarrival,
                inputApplication.delaySensitivity,
                inputApplication.activePeriod,
                inputApplication.idlePeriod,
                inputApplication.dataUpload,
                inputApplication.dataDownload,
                inputApplication.taskLength,
                inputApplication.requiredCore,
                inputApplication.vmUtilizationOnEdge,
                inputApplication.vmUtilizationOnCloud,
                inputApplication.vmUtilizationOnMobile,
            )
            return Lists.cartesianProduct(params).map {
                Application(
                    name = inputApplication.name,
                    usage_percentage = it[0].toDouble(),
                    prob_cloud_selection = it[1].toDouble(),
                    poisson_interarrival = it[2].toDouble(),
                    delay_sensitivity = it[3].toDouble(),
                    active_period = it[4].toDouble(),
                    idle_period = it[5].toDouble(),
                    data_upload = it[6].toDouble(),
                    data_download = it[7].toDouble(),
                    task_length = it[8].toDouble(),
                    required_core = it[9].toInt(),
                    vm_utilization_on_edge = it[10].toDouble(),
                    vm_utilization_on_cloud = it[11].toDouble(),
                    vm_utilization_on_mobile = it[12].toDouble(),
                )
            }
        }
    }

    fun toXml() =
        "<?xml version=\"1.0\"?>\n" +
        "<applications>\n" +
        deploy.map { it.toXml("\t") }.reduce {s1, s2 -> "$s1\n$s2"} + "\n" +
        "</applications>"
}

data class Application(
    val name: String,
    val usage_percentage: Double,
    val prob_cloud_selection: Double,
    val poisson_interarrival: Double,
    val delay_sensitivity: Double,
    val active_period: Double,
    val idle_period: Double,
    val data_upload: Double,
    val data_download: Double,
    val task_length: Double,
    val required_core: Int,
    val vm_utilization_on_edge: Double,
    val vm_utilization_on_cloud: Double,
    val vm_utilization_on_mobile: Double,
) {
    fun toXml(indent: String) =
        "$indent<application name=\"$name\">\n" +
        "$indent\t<usage_percentage>$usage_percentage</usage_percentage>\n" +
        "$indent\t<prob_cloud_selection>$prob_cloud_selection</prob_cloud_selection>\n" +
        "$indent\t<poisson_interarrival>$poisson_interarrival</poisson_interarrival>\n" +
        "$indent\t<delay_sensitivity>$delay_sensitivity</delay_sensitivity>\n" +
        "$indent\t<active_period>$active_period</active_period>\n" +
        "$indent\t<idle_period>$idle_period</idle_period>\n" +
        "$indent\t<data_upload>${BigDecimal(data_upload).setScale(3, RoundingMode.CEILING)}</data_upload>\n" +
        "$indent\t<data_download>${BigDecimal(data_download).setScale(3, RoundingMode.CEILING)}</data_download>\n" +
        "$indent\t<task_length>${BigDecimal(task_length).setScale(3, RoundingMode.CEILING)}</task_length>\n" +
        "$indent\t<required_core>$required_core</required_core>\n" +
        "$indent\t<vm_utilization_on_edge>$vm_utilization_on_edge</vm_utilization_on_edge>\n" +
        "$indent\t<vm_utilization_on_cloud>$vm_utilization_on_cloud</vm_utilization_on_cloud>\n" +
        "$indent\t<vm_utilization_on_mobile>$vm_utilization_on_mobile</vm_utilization_on_mobile>\n" +
        "$indent</application>"
}