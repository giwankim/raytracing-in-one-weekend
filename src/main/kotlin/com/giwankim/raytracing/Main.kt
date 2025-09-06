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

/**
 * Tests whether a ray intersects a sphere.
 *
 * Solves the quadratic obtained by substituting the ray `P(t) = origin + t * direction`
 * into the sphere equation `|P - center|^2 = radius^2` and checks the discriminant.
 * Returns `true` if the discriminant \> 0 (two intersections). Tangential hits
 * (discriminant == 0) and misses return `false`. Does not verify that the hit is at `t > 0`.
 *
 * @param center Sphere center in world space.
 * @param radius Sphere radius.
 * @param ray Ray to test.
 * @return `true` if the ray intersects the sphere, otherwise `false`.
 */
fun hitSphere(
    center: Point3,
    radius: Double,
    ray: Ray,
): Boolean {
    val originToCenter = center - ray.origin
    val a = ray.direction dot ray.direction
    val b = -2 * (ray.direction dot originToCenter)
    val c = (originToCenter dot originToCenter) - radius * radius
    val discriminant = b * b - 4 * a * c
    return discriminant > 0
}

fun Ray.color(): Color {
    if (hitSphere(Point3(0.0, 0.0, -1.0), 0.5, this)) {
        return Color.RED
    }

    val unitDirection = direction.normalized()
    val a = 0.5 * (unitDirection.y + 1.0)
    return (1 - a) * Color.WHITE + a * Color.BLUE
}
