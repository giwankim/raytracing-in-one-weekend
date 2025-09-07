package com.giwankim.raytracing

class HittableList(
    private val objects: MutableList<Hittable> = mutableListOf(),
) : Hittable {
    fun clear() = objects.clear()

    fun add(obj: Hittable) = objects.add(obj)

    fun addAll(objs: Collection<Hittable>): Boolean = objects.addAll(objs)

    override fun hit(
        ray: Ray,
        rayT: Interval,
    ): HitRecord? =
        objects.fold(null as HitRecord?) { best, obj ->
            // prunes the search to the closest hit so far
            val closest = best?.t ?: rayT.max
            obj.hit(ray, Interval(rayT.min, closest)) ?: best
        }
}

fun hittableListOf(vararg hs: Hittable) = HittableList(hs.toMutableList())
