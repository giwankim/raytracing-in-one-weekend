package com.giwankim.raytracing

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HittableListTest {
    @Test
    fun `hit returns null when list is empty`() {
        val list = HittableList()
        val ray = Ray(Point3.ZERO, Vec3(0, 0, 1))

        list.hit(ray, Interval.UNIVERSE) shouldBe null
    }

    @Test
    fun `hit returns nearest hit among objects`() {
        val list =
            hittableListOf(
                Sphere(Point3(0, 0, 5), 1.0),
                Sphere(Point3(0, 0, 8), 1.0),
            )
        val ray = Ray(Point3.ZERO, Vec3(0, 0, 1))

        val hit = list.hit(ray, Interval.UNIVERSE)

        requireNotNull(hit)

        assertSoftly {
            hit.t shouldBe 4.0
            hit.point shouldBe Point3(0, 0, 4)
        }
    }

    @Test
    fun `hit returns null when all hits are outside range`() {
        val list =
            hittableListOf(
                Sphere(Point3(0, 0, 5), 1.0),
                Sphere(Point3(0, 0, 8), 1.0),
            )
        val ray = Ray(Point3.ZERO, Vec3(0, 0, 1))
        val rayT = Interval(0.0, 3.9)

        val hit = list.hit(ray, rayT)

        hit shouldBe null
    }

    @Test
    fun `finds hit within range`() {
        val list =
            hittableListOf(
                Sphere(Point3(0, 0, 5), 1.0),
                Sphere(Point3(0, 0, 8), 1.0),
            )
        val ray = Ray(Point3.ZERO, Vec3(0, 0, 1))
        val rayT = Interval(6.5, 8.0)

        val hit = list.hit(ray, rayT)

        requireNotNull(hit)

        assertSoftly {
            hit.t shouldBe 7.0
            hit.point shouldBe Point3(0, 0, 7)
        }
    }
}
