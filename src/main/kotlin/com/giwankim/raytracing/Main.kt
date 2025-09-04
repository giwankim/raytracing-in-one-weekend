package com.giwankim.raytracing

fun main() {
    // Image

    val aspectRatio = 16.0 / 9.0
    val imageWidth = 400

    // Calculate the image height, and ensure that it's at least 1.
    val imageHeight =
        (imageWidth / aspectRatio)
            .toInt()
            .coerceAtLeast(1)

    // Camera

    val focalLength = 1.0
    val viewportHeight = 2.0
    val viewportWidth = viewportHeight * (imageWidth.toDouble() / imageHeight)
    val cameraCenter = Point3.ZERO

    // Calculate the vectors across the horizontal and down the vertical viewport edges.
    val viewportU = Vec3(viewportWidth, 0.0, 0.0)
    val viewportV = Vec3(0.0, -viewportHeight, 0.0)

    // Calculate the horizontal and vertical delta vectors from pixel to pixel.
    val pixelDeltaU = viewportU / imageWidth
    val pixelDeltaV = viewportV / imageHeight

    // Calculate the location of the upper left pixel.
    val viewportUpperLeft =
        cameraCenter - Vec3(0.0, 0.0, focalLength) - viewportU / 2 - viewportV / 2
    val pixel00Loc = viewportUpperLeft + 0.5 * (pixelDeltaU + pixelDeltaV)

    // Render

    System.out.bufferedWriter().use { out ->
        out
            .appendLine("P3")
            .appendLine("$imageWidth $imageHeight")
            .appendLine("255")
        for (j in 0 until imageHeight) {
            System.err.print("\rScanlines remaining: ${imageHeight - j} ")
            System.err.flush()

            for (i in 0 until imageWidth) {
                val pixelCenter = pixel00Loc + (i * pixelDeltaU) + (j * pixelDeltaV)
                val rayDirection = pixelCenter - cameraCenter
                val ray = Ray(cameraCenter, rayDirection)

                val pixelColor = rayColor(ray)
                pixelColor.write(out)
            }
        }
    }

    System.err.println("\rDone.                 ")
}

fun rayColor(ray: Ray): Color {
    val unitDirection = ray.direction.normalized()
    val a = 0.5 * (unitDirection.y + 1.0)
    return (1 - a) * Color.WHITE + a * Color.BLUE
}
