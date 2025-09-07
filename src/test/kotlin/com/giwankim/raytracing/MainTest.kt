package com.giwankim.raytracing

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junitpioneer.jupiter.StdIo
import org.junitpioneer.jupiter.StdOut
import java.io.StringWriter

@Disabled
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

    @ParameterizedTest(name = "({0}, {1}, {2}) -> {3}")
    @CsvSource(
        // r,   g,   b,   expected
        "0.0, 0.0, 0.0, 0 0 0",
        "1.0,  1.0, 1.0,  255 255 255", // 256 clamped to 255
        "0.5,  0.5, 0.5,  128 128 128", // 256 * 0.5 = 128
        "0.5,  0.7, 1.0,  128 179 255",
    )
    fun `write outputs PPM color bytes 0 to 255`(
        r: Double,
        g: Double,
        b: Double,
        expectedLine: String,
    ) {
        val color = Color(r, g, b)

        val writer = StringWriter()
        color.write(writer)

        writer.toString().trim() shouldBe expectedLine
    }
}
