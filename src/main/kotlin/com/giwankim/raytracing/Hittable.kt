package com.giwankim.raytracing

class HitRecord private constructor(
    val point: Point3,
    val normal: Vec3,
    val t: Double,
    val frontFace: Boolean,
) {
    companion object {
        fun of(
            point: Point3,
            outwardNormal: Vec3,
            ray: Ray,
            t: Double,
        ): HitRecord {
            // Sets the hit record normal vector.
            // NOTE: the parameter `outwardNormal` is assumed to have unit length.
            val frontFace = ray.direction dot outwardNormal < 0.0
            val normal = if (frontFace) outwardNormal else -outwardNormal
            return HitRecord(point, normal, t, frontFace)
        }
    }
}

fun interface Hittable {
    fun hit(
        ray: Ray,
        rayTMin: Double,
        rayTMax: Double,
    ): HitRecord?
}
