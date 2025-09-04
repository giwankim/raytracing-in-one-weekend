package com.giwankim.raytracing

import java.io.Writer

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
    }
}

operator fun Double.times(color: Color): Color = color * this

operator fun Int.times(color: Color): Color = color * this.toDouble()

fun Color.write(writer: Writer) {
    // Translate the [0,1] component values to the byte range [0,255].
    val rByte = (256.0 * r).toInt().coerceAtMost(255)
    val gByte = (256.0 * g).toInt().coerceAtMost(255)
    val bByte = (256.0 * b).toInt().coerceAtMost(255)

    // Write out the pixel color components.
    writer.appendLine("$rByte $gByte $bByte")
}
