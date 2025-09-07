package com.giwankim.raytracing

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ColorTest {
    @Test
    fun constructor() {
        // valid boundaries
        Color(0.0, 0.0, 0.0) // should not throw
        Color(1.0, 1.0, 1.0) // should not throw
        Color(0.3, 0.6, 0.9) // should not throw

        // invalid values: expect IllegalStateException from check()
        assertSoftly {
            shouldThrow<IllegalStateException> { Color(-0.00001, 0.0, 0.0) }
            shouldThrow<IllegalStateException> { Color(0.0, 1.00001, 0.0) }
            shouldThrow<IllegalStateException> { Color(0.0, 0.0, 2.0) }
        }
    }

    @Test
    fun plus() {
        val a = Color(0.2, 0.3, 0.4)
        val b = Color(0.3, 0.4, 0.5)

        (a + b) shouldBe Color(0.5, 0.7, 0.9)
    }

    @Test
    fun times() {
        val a = Color(0.2, 0.3, 0.4)

        assertSoftly {
            (a * 2.0) shouldBe Color(0.4, 0.6, 0.8)
            (a * 2) shouldBe Color(0.4, 0.6, 0.8)
            (2.0 * a) shouldBe Color(0.4, 0.6, 0.8)
            (2 * a) shouldBe Color(0.4, 0.6, 0.8)
        }
    }
}
