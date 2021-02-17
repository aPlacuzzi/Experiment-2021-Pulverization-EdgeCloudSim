package it.unibo.configgenerator.controller

import com.uchuhimo.konf.Config
import com.uchuhimo.konf.source.yaml
import it.unibo.configgenerator.model.*
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.encodeToMap
import java.io.File

object ConfigGenerator {
    fun generateConfig(inputFile: File, outputDirPath: String, protelisMips: Double, messageSize: Double) {
        val outputDir = File(outputDirPath).also { it.mkdir() }
        val generalConfigDir = File(outputDir, "generalConfig").also { it.mkdir() }
        val edgeDir = File(outputDir, "edgeConfig").also { it.mkdir() }
        val deploysDir = File(outputDir, "deploysConfig").also { it.mkdir() }
        val resultDir = File(outputDir, "results").also { it.mkdir() }

        val inputConfig = Config {
            addSpec(InputGeneralConfig)
            addSpec(InputEdgesConfig)
            addSpec(InputDeployConfig)
        }.from.yaml.file(inputFile)

        val outputGeneralConfig = OutputGeneralConfig.fromInputModel(inputConfig[InputGeneralConfig.generalConfig])
        val outputEdgeConfigs = OutputEdgeConfig.fromInputEdgeConfig(inputConfig[InputEdgesConfig.edgesConfig])
        val inputDeployConfig = inputConfig[InputDeployConfig.inputDeployConfig]

        //deviceCount -> onlyCommunication, behaviourWithCommunication, behaviourAndCommunication
        var applicationByDeviceCount = mapOf<Int, Triple<List<String>, List<String>, List<String>>>()
        // generalConfig, application config
        var configFileNames = listOf<Pair<String, String>>()
        val edgeFileName = "edge_devices.xml"
        val onlyCommunicationDeployKey = "onlyCommunication"
        val behaviourWithCommunicationDeployKey = "behaviourWithCommunication"
        val behaviourAndCommunicationDeployKey = "behaviourAndCommunication"

        outputGeneralConfig[0].singularizeDeviceCount().forEach {
            val deviceCount = it.min_number_of_mobile_devices
            val onlyCommunicationFiles = OutputDeployConfig
                .onlyCommunication(inputDeployConfig, deviceCount, outputEdgeConfigs.edge_devices.size, messageSize)
                .mapIndexed { index, element ->
                    val fileName = "$onlyCommunicationDeployKey${deviceCount}_${index}.xml"
                    File(deploysDir, fileName).also { file ->
                        file.createNewFile()
                        file.writeText(element.toXml())
                    }
                    return@mapIndexed fileName
                }
            val behaviourWithCommunicationFiles = OutputDeployConfig
                .behaviourWithCommunication(inputDeployConfig, deviceCount, outputEdgeConfigs.edge_devices.size, protelisMips, messageSize)
                .mapIndexed { index, element ->
                    val fileName = "$behaviourWithCommunicationDeployKey${deviceCount}_${index}.xml"
                    File(deploysDir, fileName).also { file ->
                        file.createNewFile()
                        file.writeText(element.toXml())
                    }
                    return@mapIndexed fileName
                }
            val behaviourAndCommunicationFiles = OutputDeployConfig
                .behaviourAndCommunication(inputDeployConfig, deviceCount, outputEdgeConfigs.edge_devices.size, protelisMips, messageSize)
                .mapIndexed { index, element ->
                    val fileName = "$behaviourAndCommunicationDeployKey${deviceCount}_${index}.xml"
                    File(deploysDir, fileName).also { file ->
                        file.createNewFile()
                        file.writeText(element.toXml())
                    }
                    return@mapIndexed fileName
                }
            applicationByDeviceCount += Pair(deviceCount, Triple(onlyCommunicationFiles, behaviourWithCommunicationFiles, behaviourAndCommunicationFiles))
        }

        outputGeneralConfig.forEachIndexed { index, element ->
            element.singularizeDeviceCount().forEach {
                val deviceCount = it.min_number_of_mobile_devices
                val fileName = "generalConfig${deviceCount}_$index.properties"
                File(generalConfigDir, fileName).also { file ->
                    file.createNewFile()
                    file.writeText(Properties.encodeToMap(it).map { (k, v) -> "$k = $v" }.reduce{ s1, s2 -> "$s1\n$s2"})
                }
                configFileNames += applicationByDeviceCount[deviceCount]!!.first.map { deployFile -> Pair(fileName, deployFile) }
                configFileNames += applicationByDeviceCount[deviceCount]!!.second.map { deployFile -> Pair(fileName, deployFile) }
                configFileNames += applicationByDeviceCount[deviceCount]!!.third.map { deployFile -> Pair(fileName, deployFile) }
            }
        }

        File(edgeDir, edgeFileName).also {
            it.createNewFile()
            it.writeText(outputEdgeConfigs.toXml())
        }

        var onlyCount = 0
        var withCount = 0
        var andCount = 0
        val simulations = configFileNames
            .map {
                val firstKey = it.first.takeWhile { c: Char -> !c.isDigit() } + it.first.substring(it.first.indexOf("_"))
                val secondKey = it.second.takeWhile { c: Char -> !c.isDigit() } + it.second.substring(it.second.indexOf("_"))
                return@map Pair(Pair(firstKey, secondKey), it)
            }
            .groupBy ({ it.first }, { it.second })
            .flatMap { entry ->
                var configResultDir = ""
                if (entry.value[0].second.startsWith(onlyCommunicationDeployKey)){
                    configResultDir = "$onlyCommunicationDeployKey${onlyCount++}"
                }
                if (entry.value[0].second.startsWith(behaviourWithCommunicationDeployKey)){
                    configResultDir = "$behaviourWithCommunicationDeployKey${withCount++}"
                }
                if (entry.value[0].second.startsWith(behaviourAndCommunicationDeployKey)){
                    configResultDir = "$behaviourAndCommunicationDeployKey${andCount++}"
                }
                return@flatMap entry.value.map { "${File(resultDir, configResultDir).relativeTo(outputDir)}," +
                        "${File(generalConfigDir, it.first).relativeTo(outputDir)}," +
                        "${File(edgeDir, edgeFileName).relativeTo(outputDir)}," +
                        "${File(deploysDir, it.second).relativeTo(outputDir)}" }
            }
            .reduce { s1, s2 -> "$s1\n$s2" }


        val header = "# output folder, general config, edge config, application config"
        File(outputDir, "simulations.csv").also {
            it.createNewFile()
            it.writeText("$header\n$simulations")
        }
    }
}