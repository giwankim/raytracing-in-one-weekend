package com.giwankim.raytracing

import kotlin.math.sqrt

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
        repeat(imageHeight) { j ->
            System.err.print("\rScanlines remaining: ${imageHeight - j} ")
            System.err.flush()

            repeat(imageWidth) { i ->
                val pixelCenter = pixel00Loc + (i * pixelDeltaU) + (j * pixelDeltaV)
                val rayDirection = pixelCenter - cameraCenter
                val ray = Ray(cameraCenter, rayDirection)

                val pixelColor = ray.color()
                pixelColor.write(out)
            }
        }
    }

    System.err.println("\rDone.                 ")
}

fun hitSphere(
    center: Point3,
    radius: Double,
    ray: Ray,
): Double {
    val originToCenter = center - ray.origin
    val a = ray.direction.lengthSquared()
    val h = ray.direction dot originToCenter
    val c = originToCenter.lengthSquared() - radius * radius
    val discriminant = h * h - a * c
    return if (discriminant < 0) {
        -1.0
    } else {
        (h - sqrt(discriminant)) / a
    }
}

fun Ray.color(): Color {
    val t =
        hitSphere(
            Point3(0.0, 0.0, -1.0),
            0.5,
            this,
        )
    if (t > 0.0) {
        val unitNormal =
            (at(t) - Vec3(0.0, 0.0, -1.0))
                .normalized()
        return Color(
            0.5 * (unitNormal.x + 1),
            0.5 * (unitNormal.y + 1),
            0.5 * (unitNormal.z + 1),
        )
    }

    val unitDirection = direction.normalized()
    val a = 0.5 * (unitDirection.y + 1.0)
    return (1 - a) * Color.WHITE + a * Color.BLUE
}
