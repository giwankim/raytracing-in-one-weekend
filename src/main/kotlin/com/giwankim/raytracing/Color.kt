package com.giwankim.raytracing

import java.io.Writer

typealias Color = Vec3

fun Color.write(writer: Writer) {
    val r = x.coerceIn(0.0, 1.0)
    val g = y.coerceIn(0.0, 1.0)
    val b = z.coerceIn(0.0, 1.0)

    // Translate the [0,1] component values to the byte range [0,255].
    val rByte = (256.0 * r).toInt().coerceAtMost(255)
    val gByte = (256.0 * g).toInt().coerceAtMost(255)
    val bByte = (256.0 * b).toInt().coerceAtMost(255)

    // Write out the pixel color components.
    writer.appendLine("$rByte $gByte $bByte")
}
