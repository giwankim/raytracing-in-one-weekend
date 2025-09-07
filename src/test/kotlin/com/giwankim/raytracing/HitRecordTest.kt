package com.giwankim.raytracing

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HitRecordTest {
    @Test
    fun `of sets frontFace true when ray hits front face`() {
        val ray = Ray(Point3.ZERO, Vec3(0, 0, -1))
        val outwardNormal = Vec3(0, 0, 1)
        val point = Point3(0, 0, -1)
        val t = 1.0

        val hit = HitRecord.of(point, outwardNormal, ray, t)

        hit.frontFace shouldBe true
        hit.normal shouldBe outwardNormal
    }

    @Test
    fun `of sets frontFace false when ray hits back face`() {
        val ray = Ray(Point3.ZERO, Vec3(0, 0, 1))
        val outwardNormal = Vec3(0, 0, 1)
        val point = Point3(0, 0, 1)
        val t = 1.0

        val hit = HitRecord.of(point, outwardNormal, ray, t)

        hit.frontFace shouldBe false
        hit.normal shouldBe -outwardNormal
    }
}
