package com.giwankim.raytracing

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Vec3Test {
    @Test
    fun unaryMinus() {
        val v = -Vec3(1.0, 2.0, 3.0)

        v shouldBe Vec3(-1.0, -2.0, -3.0)
    }

    @Test
    fun get() {
        val v = Vec3(1.0, 2.0, 3.0)

        assertSoftly {
            v[0] shouldBe 1.0
            v[1] shouldBe 2.0
            v[2] shouldBe 3.0
        }
    }

    @Test
    fun lengthSquared() {
        val v = Vec3(1.0, 2.0, 3.0)

        v.lengthSquared() shouldBe 14.0
    }

    @Test
    fun length() {
        val v = Vec3(3.0, 4.0, 0.0)

        v.length shouldBe 5.0
    }

    @Test
    fun plus() {
        val v = Vec3(1.0, 2.0, 3.0)
        val w = Vec3(4.0, 5.0, 6.0)

        (v + w) shouldBe Vec3(5.0, 7.0, 9.0)
    }

    @Test
    fun minus() {
        val v = Vec3(5.0, 7.0, 9.0)
        val w = Vec3(4.0, 5.0, 6.0)

        (v - w) shouldBe Vec3(1.0, 2.0, 3.0)
    }

    @Test
    fun times() {
        val v = Vec3(1.0, 2.0, 3.0)
        val w = Vec3(4.0, 5.0, 6.0)

        assertSoftly {
            // component-wise multiplication
            (v * w) shouldBe Vec3(4.0, 10.0, 18.0)
            // scalar on right
            (v * 2.0) shouldBe Vec3(2.0, 4.0, 6.0)
            // scalar on left
            (2.0 * v) shouldBe Vec3(2.0, 4.0, 6.0)
        }
    }

    @Test
    fun div() {
        val v = Vec3(1.0, 2.0, 3.0)

        (v / 2.0) shouldBe Vec3(0.5, 1.0, 1.5)
    }

    @Test
    fun dot() {
        val v = Vec3(1.0, 2.0, 3.0)
        val w = Vec3(4.0, 5.0, 6.0)

        (v dot w) shouldBe 32.0
    }

    @Test
    fun cross() {
        val i = Vec3(1.0, 0.0, 0.0)
        val j = Vec3(0.0, 1.0, 0.0)
        val k = Vec3(0.0, 0.0, 1.0)

        val eps = 1e-12

        assertSoftly {
            (i cross j).x shouldBe k.x plusOrMinus eps
            (i cross j).y shouldBe k.y plusOrMinus eps
            (i cross j).z shouldBe k.z plusOrMinus eps
            (j cross i).x shouldBe (-k).x plusOrMinus eps
            (j cross i).y shouldBe (-k).y plusOrMinus eps
            (j cross i).z shouldBe (-k).z plusOrMinus eps
        }
    }

    @Test
    fun normalized() {
        val v = Vec3(1.0, 2.0, 2.0) // length = 3
        val u = v.normalized()

        val eps = 1e-12

        assertSoftly {
            u.x shouldBe (1.0 / 3.0) plusOrMinus eps
            u.y shouldBe (2.0 / 3.0) plusOrMinus eps
            u.z shouldBe (2.0 / 3.0) plusOrMinus eps
            u.length shouldBe 1.0 plusOrMinus eps
        }
    }
}
