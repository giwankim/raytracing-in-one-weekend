package com.giwankim.raytracing

import kotlin.math.sqrt

data class Vec3(
    val x: Double = 0.0,
    val y: Double = 0.0,
    val z: Double = 0.0,
) {
    operator fun unaryMinus(): Vec3 = Vec3(-x, -y, -z)

    operator fun get(i: Int): Double =
        when (i) {
            0 -> x
            1 -> y
            2 -> z
            else -> throw IndexOutOfBoundsException("Index $i out of range [0, 2]")
        }

    val length: Double get() = sqrt(lengthSquared())

    fun lengthSquared(): Double = x * x + y * y + z * z

    operator fun plus(other: Vec3): Vec3 = Vec3(x + other.x, y + other.y, z + other.z)

    operator fun minus(other: Vec3): Vec3 = Vec3(x - other.x, y - other.y, z - other.z)

    operator fun times(other: Vec3): Vec3 = Vec3(x * other.x, y * other.y, z * other.z)

    operator fun times(t: Double): Vec3 =
        Vec3(
            t * x,
            t * y,
            t * z,
        )

    operator fun div(t: Double): Vec3 {
        require(t != 0.0) { "Cannot divide by zero" }
        return (1 / t) * Vec3(x, y, z)
    }

    operator fun div(t: Int): Vec3 = this / t.toDouble()

    infix fun dot(other: Vec3): Double = x * other.x + y * other.y + z * other.z

    infix fun cross(other: Vec3): Vec3 =
        Vec3(
            y * other.z - z * other.y,
            z * other.x - x * other.z,
            x * other.y - y * other.x,
        )

    fun normalized(): Vec3 {
        require(length != 0.0) { "Cannot normalize a zero-length vector" }
        return this / length
    }

    companion object {
        val ZERO = Vec3(0.0, 0.0, 0.0)
    }
}

operator fun Double.times(vec: Vec3): Vec3 = vec * this

operator fun Int.times(vec: Vec3): Vec3 = vec * this.toDouble()

typealias Point3 = Vec3
