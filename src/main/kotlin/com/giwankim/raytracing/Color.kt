package com.giwankim.raytracing

data class Color(
    val r: Double,
    val g: Double,
    val b: Double,
) {
    init {
        check(r in 0.0..1.0) { "r must be in [0.0, 1.0], was $r" }
        check(g in 0.0..1.0) { "g must be in [0.0, 1.0], was $g" }
        check(b in 0.0..1.0) { "b must be in [0.0, 1.0], was $b" }
    }

    operator fun plus(other: Color): Color = Color(r + other.r, g + other.g, b + other.b)

    operator fun times(t: Double): Color =
        Color(
            t * r,
            t * g,
            t * b,
        )

    operator fun times(t: Int): Color = this * t.toDouble()

    companion object {
        val WHITE = Color(1.0, 1.0, 1.0)
        val BLUE = Color(0.5, 0.7, 1.0)
        val RED = Color(1.0, 0.0, 0.0)
    }
}

operator fun Double.times(color: Color): Color = color * this

operator fun Int.times(color: Color): Color = color * this.toDouble()
