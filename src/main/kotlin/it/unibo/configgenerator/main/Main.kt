package it.unibo.configgenerator.main

import com.google.common.collect.Lists
import com.uchuhimo.konf.Config
import com.uchuhimo.konf.source.yaml
import it.unibo.configgenerator.model.*
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.encodeToMap
import java.io.File

fun main() {
    val inputFile = File("C:\\Users\\Placu\\softcomp\\EdgeCloudSimConfigGenerator\\src\\main\\resources\\config1.yml")
    val generalConfigDir = File(inputFile.parent, "generalConfig").also { it.mkdir() }
    val edgeDir = File(inputFile.parent, "edgeConfig").also { it.mkdir() }
    val deploysDir = File(inputFile.parent, "deploysConfig").also { it.mkdir() }

    val inputConfig = Config {
        addSpec(InputGeneralConfig)
        addSpec(InputEdgesConfig)
        addSpec(InputDeployConfig)
    }.from.yaml.file(inputFile)

    val outputGeneralConfig = OutputGeneralConfig.fromInputModel(inputConfig[InputGeneralConfig.generalConfig])
    val outputEdgeConfigs = OutputEdgeConfig.fromInputEdgeConfig(inputConfig[InputEdgesConfig.edgesConfig])
    val outputDeploy = OutputDeployConfig.fromInputConfig(inputConfig[InputDeployConfig.inputDeployConfig])

    var generalConfigFileNames = listOf<String>()
    var applicationsFileNames = listOf<String>()
    val edgeFileName = "edge_devices.xml"

    outputGeneralConfig.forEachIndexed { index, element ->
        val fileName = "generalConfig$index.properties"
        File(generalConfigDir, fileName).also {
            it.createNewFile()
            it.writeText(Properties.encodeToMap(element).map { (k, v) -> "$k = $v" }.reduce{s1, s2 -> "$s1\n$s2"})
        }
        generalConfigFileNames += fileName
    }

    File(edgeDir, edgeFileName).also {
        it.createNewFile()
        it.writeText(outputEdgeConfigs.toXml())
    }

    outputDeploy.forEachIndexed { index, element ->
        val fileName = "applications$index.xml"
        File(deploysDir, fileName).also {
            it.createNewFile()
            it.writeText(element.toXml())
        }
        applicationsFileNames += fileName
    }

    val header = "# output folder; general config; edge config; application config"
    val simulations = Lists.cartesianProduct(generalConfigFileNames, applicationsFileNames)
        .mapIndexed {index, list -> "config$index;${list[0]};$edgeFileName;${list[1]}" }
        .reduce { s1, s2 -> "$s1\n$s2" }
    File(inputFile.parent, "simulations.csv").also {
        it.createNewFile()
        it.writeText("$header\n$simulations")
    }
}