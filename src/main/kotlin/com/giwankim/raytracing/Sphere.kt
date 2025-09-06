package com.giwankim.raytracing

import kotlin.math.sqrt

class Sphere(
    val center: Point3,
    radius: Double,
) : Hittable {
    val radius: Double = radius.coerceAtLeast(0.0)

    override fun hit(
        ray: Ray,
        rayTMin: Double,
        rayTMax: Double,
    ): HitRecord? {
        val originToCenter = ray.origin - center
        val a = ray.direction.lengthSquared()
        val h = ray.direction dot originToCenter // b = -2 * h
        val c = originToCenter.lengthSquared() - radius * radius

        val discriminant = h * h - a * c
        if (discriminant < 0.0) {
            return null
        }

        val sqrtDiscriminant = sqrt(discriminant)

        // Find the nearest root that lies in the acceptable range.
        val root = (
            listOf(
                (-h - sqrtDiscriminant) / a,
                (-h + sqrtDiscriminant) / a,
            ).firstOrNull { it > rayTMin && it < rayTMax }
                ?: return null
        )

        val point = ray.at(root)
        val normal = (point - center) / radius
        return HitRecord(point, normal, root)
    }
}
