package com.giwankim.raytracing

data class Ray(
    val origin: Point3,
    val direction: Vec3,
) {
    fun at(t: Double): Point3 = origin + direction * t
}
