package com.giwankim.raytracing

class HittableList(
    private val objects: MutableList<Hittable> = mutableListOf(),
) : Hittable {
    fun clear() = objects.clear()

    fun add(obj: Hittable) = objects.add(obj)

    fun addAll(objs: Collection<Hittable>): Boolean = objects.addAll(objs)

    override fun hit(
        ray: Ray,
        rayTMin: Double,
        rayTMax: Double,
    ): HitRecord? =
        objects.fold(null as HitRecord?) { best, obj ->
            val closest = best?.t ?: rayTMax
            obj.hit(ray, rayTMin, closest) ?: best
        }
}

fun hittableListOf(vararg hs: Hittable) = HittableList(hs.toMutableList())
