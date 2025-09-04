package com.giwankim.raytracing

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
            lines[0] shouldBe "P3"
            lines[1] shouldBe "256 256"
            lines[2] shouldBe "255"
            lines[3] shouldBe "0 0 0"
            lines.last() shouldBe "255 255 0"
        }
    }
}
