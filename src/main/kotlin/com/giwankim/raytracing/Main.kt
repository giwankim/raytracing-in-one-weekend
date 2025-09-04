package com.giwankim.raytracing

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
            System.err.print("\rScanlines remaining: ${imageHeight - j} ")
            System.err.flush()

            for (i in 0 until imageWidth) {
                val pixelColor =
                    Color(i.toDouble() / (imageWidth - 1), j.toDouble() / (imageHeight - 1), 0.0)
                pixelColor.write(out)
            }
        }
    }

    System.err.println("\rDone.                 ")
}
