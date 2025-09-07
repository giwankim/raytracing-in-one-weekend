package com.giwankim.raytracing

import java.io.Writer

fun main() {
    // Image

    val aspectRatio = 16.0 / 9.0
    val imageWidth = 400

    // Calculate the image height, and ensure that it's at least 1.
    val imageHeight =
        (imageWidth / aspectRatio)
            .toInt()
            .coerceAtLeast(1)

    // World

    val world =
        hittableListOf(
            Sphere(Point3(0, 0, -1), 0.5),
            Sphere(Point3(0, -100.5, -1), 100.0),
        )

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

                val pixelColor = ray.color(world)
                pixelColor.write(out)
            }
        }
    }

    System.err.println("\rDone.                 ")
}

fun Ray.color(world: Hittable): Color {
    val hit = world.hit(this, 0.0, Double.POSITIVE_INFINITY)
    if (hit != null) {
        // components of hit.normal are between -1 and 1.
        // 0.5 * (normal + 1) translates the normal to the range [0,1]
        return Color(
            0.5 * (hit.normal.x + 1),
            0.5 * (hit.normal.y + 1),
            0.5 * (hit.normal.z + 1),
        )
    }

    val unitDirection = direction.normalized()
    // y component of unitDirection is between -1 and 1, so
    // a is between 0 and 1.
    val a = 0.5 * (unitDirection.y + 1.0)
    return (1 - a) * Color.WHITE + a * Color.BLUE
}

fun Color.write(writer: Writer) {
    // Translate the [0,1] component values to the byte range [0,255].
    val rByte = (256.0 * r).toInt().coerceAtMost(255)
    val gByte = (256.0 * g).toInt().coerceAtMost(255)
    val bByte = (256.0 * b).toInt().coerceAtMost(255)

    // Write out the pixel color components.
    writer.appendLine("$rByte $gByte $bByte")
}
