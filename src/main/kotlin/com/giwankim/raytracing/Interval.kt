package com.giwankim.raytracing

data class Interval(
    val min: Double,
    val max: Double,
) {
    val size: Double get() = max - min

    operator fun contains(x: Double) = x in min..max

    fun surrounds(x: Double) = x > min && x < max

    companion object {
        val EMPTY = Interval(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY)
        val UNIVERSE = Interval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)
    }
}
