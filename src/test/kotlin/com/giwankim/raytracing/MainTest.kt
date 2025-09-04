package com.giwankim.raytracing

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junitpioneer.jupiter.StdIo
import org.junitpioneer.jupiter.StdOut

class MainTest {
    @Test
    @StdIo
    fun `writes PPM to StdOut`(out: StdOut) {
        main()

        out.capturedLines().let { lines ->
            assertSoftly {
                lines[0] shouldBe "P3"
                lines[1] shouldBe "400 225"
                lines[2] shouldBe "255"
                lines[3] shouldBe "163 200 255"
                lines.last() shouldBe "220 234 255"
            }
        }
    }
}
