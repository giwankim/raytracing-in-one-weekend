package com.giwankim.raytracing

data class HitRecord(
    val point: Point3,
    val normal: Vec3,
    val t: Double,
)

fun interface Hittable {
    fun hit(
        ray: Ray,
        rayTMin: Double,
        rayTMax: Double,
    ): HitRecord?
}
