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
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:6.0.2")
    testImplementation("org.junit-pioneer:junit-pioneer:2.3.0")
    testCompileOnly("org.junit.jupiter:junit-jupiter-params:5.13.4")
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
