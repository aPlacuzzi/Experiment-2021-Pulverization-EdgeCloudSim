import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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