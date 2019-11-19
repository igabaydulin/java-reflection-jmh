# Java Reflection JMH Benchmarks

> There are three kinds of lies: lies, damned lies, and benchmarks.

Contains number of benchmarks which may help to check if reflection usage
can lead to performance issues in your application. You can easily add
your custom metrics if you need to.

## Build
```bash
./gradlew installDist
```

## Execution
```bash
./build/install/java-reflection-jmh/bin/java-reflection-jmh -f 2 -i 1 -wf 1 -wi 1
```

To get the full list of available options you can use `-h` argument:
```bash
./build/install/java-reflection-jmh/bin/java-reflection-jmh -h
```

## Output
>REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
 why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
 experiments, perform baseline and negative tests that provide experimental control, make sure
 the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
 Do not assume the numbers tell you what you want them to tell.
### JMH Average Time Output
```
Benchmark                                                 Mode  Cnt    Score   Error  Units
c.g.i.r.j.b.compile.Instance.newInstance                  avgt    2    7.156          ns/op
c.g.i.r.j.b.compile.PublicMethodDirectAccess.invoke       avgt    2    4.129          ns/op
c.g.i.r.j.b.other.HashMapBenchmark.put                    avgt    2   60.911          ns/op
c.g.i.r.j.b.other.JacksonBenchmark.jacksonMapping         avgt    2  295.002          ns/op
c.g.i.r.j.b.runtime.PrivateMethodReflectionAccess.invoke  avgt    2   12.331          ns/op
c.g.i.r.j.b.runtime.PrivateMethodReflectionAccess.lookup  avgt    2  444.542          ns/op
c.g.i.r.j.b.runtime.PublicMethodReflectionAccess.invoke   avgt    2   12.052          ns/op
c.g.i.r.j.b.runtime.PublicMethodReflectionAccess.lookup   avgt    2  442.488          ns/op
c.g.i.r.j.b.runtime.ReflectionInstance.newInstance        avgt    2   48.506          ns/op
```
### JMH Throughput Output
```
Benchmark                                                  Mode  Cnt          Score   Error  Units
c.g.i.r.j.b.compile.Instance.newInstance                  thrpt    2  139625682.413          ops/s
c.g.i.r.j.b.compile.PublicMethodDirectAccess.invoke       thrpt    2  243110754.073          ops/s
c.g.i.r.j.b.other.HashMapBenchmark.put                    thrpt    2   16464640.532          ops/s
c.g.i.r.j.b.other.JacksonBenchmark.jacksonMapping         thrpt    2    3404797.108          ops/s
c.g.i.r.j.b.runtime.PrivateMethodReflectionAccess.invoke  thrpt    2   81365373.295          ops/s
c.g.i.r.j.b.runtime.PrivateMethodReflectionAccess.lookup  thrpt    2    2212539.141          ops/s
c.g.i.r.j.b.runtime.PublicMethodReflectionAccess.invoke   thrpt    2   81499412.508          ops/s
c.g.i.r.j.b.runtime.PublicMethodReflectionAccess.lookup   thrpt    2    2267754.845          ops/s
c.g.i.r.j.b.runtime.ReflectionInstance.newInstance        thrpt    2   20401518.019          ops/s
```

## Some Thoughts
*It seems like* method lookup by reflection is the most time-consuming
thing (it is x100 slower than regular method invocation) when invocation by
reflection of a known method is only about three times slower than regular invocation.
So if it is critical method lookup can be done once and then method should be stored
somewhere. On the other hand penalty of using reflection to invoke method can be ignored.

But always remember:
> Premature optimization is the root of all evil

