plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktlint)
}

group = "com.giwankim"
version = "1.0-SNAPSHOT"
description = "Ray Tracing in One Weekend"

dependencies {
    // kotlin-test
    testImplementation(kotlin("test"))

    // Kotest
    testImplementation(libs.kotest)

    // JUnit 5
    testImplementation(libs.junit.pioneer)
    testCompileOnly(libs.junit.jupiterParams)
}

kotlin {
    jvmToolchain(
        libs.versions.java
            .get()
            .toInt(),
    )
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClass = "com.giwankim.raytracing.MainKt"
}
