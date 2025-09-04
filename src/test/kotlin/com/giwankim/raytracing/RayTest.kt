package com.giwankim.raytracing

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RayTest {
    @Test
    fun at() {
        val ray = Ray(Point3(2.0, 3.0, 4.0), Vec3(1.0, 0.0, 0.0))
        assertSoftly {
            ray.at(0.0) shouldBe Point3(2.0, 3.0, 4.0)
            ray.at(1.0) shouldBe Point3(3.0, 3.0, 4.0)
            ray.at(2.5) shouldBe Point3(4.5, 3.0, 4.0)
        }
    }
}
