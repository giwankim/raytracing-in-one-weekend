package com.giwankim.raytracing

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SphereTest {
    @Test
    fun `constructor coerces negative radius to zero`() {
        Sphere(Point3.ZERO, -0.1).radius shouldBe 0.0
        Sphere(Point3.ZERO, -1.5).radius shouldBe 0.0
    }

    @Test
    fun `hit returns null when both roots are outside the range`() {
        val sphere = Sphere(Point3.ZERO, 1.0)
        val ray = Ray(Point3(0, 0, 5), Vec3(0, 0, -1))

        // tMax is below the near root (4.0)
        val miss = sphere.hit(ray, Interval(min = 0.0, max = 3.9))

        miss shouldBe null
    }

    @Test
    fun `hit returns null when ray misses the sphere`() {
        val sphere = Sphere(Point3.ZERO, 1.0)
        val ray = Ray(Point3(0, 0, 5), Vec3(1, 0, 0))

        val miss = sphere.hit(ray, Interval(min = 0.1, max = Double.POSITIVE_INFINITY))

        miss shouldBe null
    }

    @Test
    fun `hit returns nearest root within range`() {
        val sphere = Sphere(Point3.ZERO, 1.0)
        val ray = Ray(Point3(0, 0, 5), Vec3(0, 0, -1))

        val hit = sphere.hit(ray, Interval(min = 0.0, max = Double.POSITIVE_INFINITY))

        requireNotNull(hit)

        assertSoftly {
            hit.point shouldBe Point3(0, 0, 1)
            hit.normal shouldBe Vec3(0, 0, 1)
            hit.t shouldBe 4.0
        }
    }

    @Test
    fun `hit returns far root when near root is below tMin`() {
        val sphere = Sphere(Point3.ZERO, 1.0)
        val ray = Ray(Point3(0, 0, 5), Vec3(0, 0, -1))

        val hit = sphere.hit(ray, Interval(min = 4.1, max = Double.POSITIVE_INFINITY))

        requireNotNull(hit)

        assertSoftly {
            hit.point shouldBe Point3(0, 0, -1)
            hit.normal shouldBe -Vec3(0, 0, -1)
            hit.t shouldBe 6.0
        }
    }
}
