package com.giwankim.raytracing

data class Ray(
    private val origin: Point3,
    private val direction: Vec3,
) {
    fun at(t: Double): Point3 = origin + direction * t
}
