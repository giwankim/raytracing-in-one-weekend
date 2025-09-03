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
