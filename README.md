# Java Reflection JMH Benchmarks [![Version](https://img.shields.io/badge/Version-1.0.1-color.svg)](https://github.com/igabaydulin/java-reflection-jmh/releases/tag/1.0.0) 

> There are three kinds of lies: lies, damned lies, and benchmarks.

Contains number of benchmarks which may help to check if reflection usage
can lead to performance issues in your application. You can easily add
your custom metrics if you need to.

## Table of Contents
* [Download](#download)
* [Running Benchmarks](#running-benchmarks)
* [Build from Sources](#build-from-sources)
* [Running from Sources](#running-from-sources)
* [Output](#output)
  * [JMH Average Time Output](#jmh-average-time-output)
  * [JMH Throughput Output](#jmh-throughput-output)
* [GitHub Actions](#github-actions)
* [Some Thoughts](#some-thoughts)

## Download
Pre-built binaries are available on GitHub [releases page](https://github.com/igabaydulin/java-reflection-jmh/releases)

## Running Benchmarks
```bash
./bin/java-reflection-jmh -f 1 -i 5 -wf 1 -wi 1 -r 1 -w 1 -to 5
```

Or
```bash
java -cp "./lib/*" org.openjdk.jmh.Main -f 1 -i 5 -wf 1 -wi 1 -r 1 -w 1 -to 5
```

Getting the full list of available options
```
./bin/java-reflection-jmh -h
```

## Build from Sources
```bash
./gradlew build
```
Built binaries are available then in `build/distributions` folder

## Running from Sources
```bash
./gradlew run --args='-f 1 -i 5 -wf 1 -wi 1 -r 1 -w 1 -to 5'
```

## Output
>REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
 why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
 experiments, perform baseline and negative tests that provide experimental control, make sure
 the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
 Do not assume the numbers tell you what you want them to tell.

### JMH Average Time Output
```
Benchmark                                                 Mode  Cnt    Score    Error  Units
c.g.i.r.j.b.compile.Instance.newInstance                  avgt    5    5.673 ±  4.094  ns/op
c.g.i.r.j.b.compile.PublicMethodDirectAccess.invoke       avgt    5    3.734 ±  1.309  ns/op
c.g.i.r.j.b.other.HashMapBenchmark.put                    avgt    5   53.841 ± 12.707  ns/op
c.g.i.r.j.b.other.JacksonBenchmark.jacksonMapping         avgt    5  324.231 ± 50.151  ns/op
c.g.i.r.j.b.runtime.PrivateMethodReflectionAccess.invoke  avgt    5    7.281 ±  1.998  ns/op
c.g.i.r.j.b.runtime.PrivateMethodReflectionAccess.lookup  avgt    5   48.090 ±  5.668  ns/op
c.g.i.r.j.b.runtime.PublicMethodReflectionAccess.invoke   avgt    5    7.108 ±  2.465  ns/op
c.g.i.r.j.b.runtime.PublicMethodReflectionAccess.lookup   avgt    5   44.933 ±  5.771  ns/op
c.g.i.r.j.b.runtime.ReflectionInstance.newInstance        avgt    5   58.226 ± 14.992  ns/op
```
### JMH Throughput Output
```
Benchmark                                                  Mode  Cnt          Score          Error  Units
c.g.i.r.j.b.compile.Instance.newInstance                  thrpt    5  174524387.108 ± 74579478.923  ops/s
c.g.i.r.j.b.compile.PublicMethodDirectAccess.invoke       thrpt    5  271857082.549 ± 86476389.138  ops/s
c.g.i.r.j.b.other.HashMapBenchmark.put                    thrpt    5   18483637.476 ±  5696877.833  ops/s
c.g.i.r.j.b.other.JacksonBenchmark.jacksonMapping         thrpt    5    3116055.582 ±   175735.272  ops/s
c.g.i.r.j.b.runtime.PrivateMethodReflectionAccess.invoke  thrpt    5  139231518.209 ± 44896326.478  ops/s
c.g.i.r.j.b.runtime.PrivateMethodReflectionAccess.lookup  thrpt    5   21975964.414 ±  2517669.138  ops/s
c.g.i.r.j.b.runtime.PublicMethodReflectionAccess.invoke   thrpt    5  140913668.335 ± 41392437.398  ops/s
c.g.i.r.j.b.runtime.PublicMethodReflectionAccess.lookup   thrpt    5   22118062.601 ±  5134828.430  ops/s
c.g.i.r.j.b.runtime.ReflectionInstance.newInstance        thrpt    5   17292500.632 ±  3342972.344  ops/s
```

## GitHub Actions
> YMMV

Benchmarks results are presented on [Actions page](https://github.com/igabaydulin/java-reflection-jmh/actions).

[More details about GitHub Actions](https://github.com/features/actions)

## Some Thoughts
*It seems like* method lookup by reflection is the most time-consuming
thing when invocation by reflection of a known method is only about three
times slower than regular invocation. So if it is critical method lookup can
be done once and then method should be stored somewhere. On the other hand
penalty of using reflection to invoke method can be ignored.

But always remember:
> Premature optimization is the root of all evil

