package com.github.igabaydulin.reflection.jmh;

import java.util.HashMap;
import java.util.Map;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class HashMapBenchmark {

  @State(Scope.Benchmark)
  public static class MapProvider {

    HashMap<String, Object> hashMap() {
      return new HashMap<>();
    }
  }

  @Benchmark
  public void put(MapProvider mapProvider, Blackhole blackhole) {
    Map<String, Object> map = mapProvider.hashMap();
    map.put("Hello", "World!");
    blackhole.consume(map);
  }
}
