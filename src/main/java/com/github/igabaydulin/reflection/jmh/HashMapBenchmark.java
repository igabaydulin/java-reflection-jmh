package com.github.igabaydulin.reflection.jmh;

import java.util.HashMap;
import java.util.Map;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

public class HashMapBenchmark {

  @State(Scope.Benchmark)
  public static class MapProvider {

    Map<String, Object> map;

    @Setup(Level.Invocation)
    public void setUp() {
      map = new HashMap<>();
    }
  }

  @Benchmark
  public void put(MapProvider mapProvider) {
    mapProvider.map.put("Hello", "World!");
  }
}
