package com.giwankim.raytracing

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main() {
    // Image
    val imageWidth = 256
    val imageHeight = 256

    System.out.bufferedWriter().use { out ->
        out.appendLine("P3")
        out.appendLine("$imageWidth $imageHeight")
        out.appendLine("255")

        // Render
        for (j in 0 until imageHeight) {
            logger.info { "Scanlines remaining: ${imageHeight - j}" }
            for (i in 0 until imageWidth) {
                val r = i.toDouble() / (imageWidth - 1)
                val g = j.toDouble() / (imageHeight - 1)
                val b = 0.0

                val ir = (255.999 * r).toInt()
                val ig = (255.999 * g).toInt()
                val ib = (255.999 * b).toInt()

                out.appendLine("$ir $ig $ib")
            }
        }
    }

    logger.info { "Done." }
}
