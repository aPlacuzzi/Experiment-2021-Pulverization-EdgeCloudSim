package it.unibo.configgenerator.model

import com.google.common.collect.Lists

data class OutputDeployConfig(val deploy: List<Application>) {
    companion object {
        fun fromInputConfig(inputDeployConfig: InputDeployConfig): List<OutputDeployConfig> =
            inputDeployConfig.deploys.map { inputDeploy ->
                Lists.cartesianProduct(inputDeploy.applications.map { allApplication(it) })
                    .map { OutputDeployConfig(it) }
            }.flatten()

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
        "$indent\t<data_upload>$data_upload</data_upload>\n" +
        "$indent\t<data_download>$data_download</data_download>\n" +
        "$indent\t<task_length>$task_length</task_length>\n" +
        "$indent\t<required_core>$required_core</required_core>\n" +
        "$indent\t<vm_utilization_on_edge>$vm_utilization_on_edge</vm_utilization_on_edge>\n" +
        "$indent\t<vm_utilization_on_cloud>$vm_utilization_on_cloud</vm_utilization_on_cloud>\n" +
        "$indent\t<vm_utilization_on_mobile>$vm_utilization_on_mobile</vm_utilization_on_mobile>\n" +
        "$indent</application>"
}