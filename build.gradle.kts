import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.concurrent.CompletableFuture
import java.io.File.separator

plugins {
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.30"
}

group = "it.unibo"
version = "1.0-SNAPSHOT"

sourceSets {
    main {
        resources {
            srcDir("src/main/protelis")
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.google.guava:guava:30.1-jre")
    implementation("com.uchuhimo:konf:1.0.0")
    implementation("org.protelis:protelis-interpreter:_")
    implementation("org.protelis:protelis-lang:_")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-properties:1.0.1")
    implementation("de.ruedigermoeller:fst:2.56")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val outputDir = File(projectDir, "output")
val resourcesDir = "${projectDir.absolutePath}${separator}src${separator}main${separator}resources"
val configFile = File(resourcesDir, "config1.yml")
val protelisProgram = "sgcg:sgcg"

tasks.register<JavaExec>("createConfigFiles") {
    dependsOn("build")
/*
    if (outputDir.exists() && outputDir.isDirectory) {
        outputDir.deleteRecursively()
    }
    outputDir.mkdir()*/

    main = "it.unibo.configgenerator.main.Main"
    args(configFile.absolutePath, outputDir.absolutePath, protelisProgram)
    classpath = sourceSets["main"].runtimeClasspath
}

class Job(
    private val runtime: Runtime,
    private val listOfFiles: ListOfFiles,
    private val jarPath: String
) : Thread() {
    val future: CompletableFuture<Int> = CompletableFuture()
    private var stop = false
    override fun run() {
        while (!stop) {
            val config = listOfFiles.getNextConfig()
            if (config == null) {
                stop = true
            } else {
                var completed = false
                var attempt = 0
                while (!completed && attempt < 2) {
                    runtime.exec(getCmd(config)).onExit().get()
                    completed = isCompleted(config)
                    attempt++
                }
            }
        }
        future.complete(0)
    }
    private fun getCmd(config: Config) =
        "java -jar $jarPath ${config.generalConfig} ${config.edgeConfig} ${config.deployConfig} ${config.resultDir} 1"

    private fun isCompleted(config: Config): Boolean {
        val deviceCount = File(config.generalConfig).name.substring(13, 16)
        return File(config.resultDir).listFiles().any { it.name.substring(31, 34) == deviceCount }
    }
}

data class Config(val resultDir: String, val generalConfig: String, val edgeConfig: String, val deployConfig: String)

class ListOfFiles(csvFile: File) {
    val files = csvFile.bufferedReader(Charsets.UTF_8)
        .lineSequence()
        .drop(1)
        .map { it.split(",") }
        .map { Config(
            resultDir = createResultDirIfAbsent(File(outputDir, it[0])),
            generalConfig = File(outputDir, it[1]).absolutePath,
            edgeConfig = File(outputDir, it[2]).absolutePath,
            deployConfig = File(outputDir, it[3]).absolutePath
        ) }
        .toMutableList()

    private fun createResultDirIfAbsent(file :File): String {
        if (!file.exists()) {
            file.mkdir()
        }
        return file.absolutePath
    }

    fun getNextConfig(): Config? {
        synchronized(this) {
            return if (files.isNotEmpty()) {
                files.removeAt(0)
            } else {
                null
            }
        }
    }
}

fun makeBatch(fileName: String, taskName: String) = tasks.register<DefaultTask>(taskName) {
    dependsOn("build")
    val jarPath = File(projectDir, "libs${separator}EdgeCloudSim.jar").absolutePath
    doLast {
        val runtime = Runtime.getRuntime()
        val files = ListOfFiles(outputDir.listFiles().first { it.name == fileName })
        val jobs = (0 until runtime.availableProcessors() - 1)
            .map { Job(runtime, files, jarPath) }
            .map { Pair(it, it.future) }
        jobs.forEach { it.first.start() }
        jobs.forEach { it.second.get() }
    }
}

makeBatch(taskName = "defaultBatch", fileName = "simulations.csv")
makeBatch(taskName = "recoverBatch", fileName = "recover.csv")
