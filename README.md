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
```
Benchmark                            Mode  Cnt          Score   Error  Units
HashMapBenchmark.put                thrpt    2   34896354.337          ops/s
JacksonBenchmark.jacksonMapping     thrpt    2     255765.043          ops/s
NoReflectionBenchmark.methodAccess  thrpt    2  294832127.902          ops/s
NoReflectionBenchmark.newInstance   thrpt    2  199842472.022          ops/s
ReflectionBenchmark.methodAccess    thrpt    2   22489610.744          ops/s
ReflectionBenchmark.newInstance     thrpt    2   20523690.388          ops/s
```
