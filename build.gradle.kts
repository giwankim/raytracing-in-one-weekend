plugins {
    application
    kotlin("jvm") version "2.2.10"
    id("org.jlleitschuh.gradle.ktlint") version "13.1.0"
}

group = "com.giwankim"
version = "1.0-SNAPSHOT"
description = "Ray Tracing in One Weekend"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.3")
    implementation("org.slf4j:slf4j-api:2.0.17")
    runtimeOnly("ch.qos.logback:logback-classic:1.5.18")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClass = "com.giwankim.raytracing.MainKt"
}
