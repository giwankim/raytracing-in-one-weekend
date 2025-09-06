# Ray Tracing in One Weekend

[Ray Tracing in One Weekend](https://raytracing.github.io/books/RayTracingInOneWeekend.html)
implemented in Kotlin.

Output image to StdOut:

```shell
./gradlew run
```

Output image to file:

```shell
./gradlew run > image.ppm
```

PPM files can be viewed with the default Preview app on macOS or on iTerm2 by using its imgcat
command:

```shell
./gradlew run | imgcat
```
