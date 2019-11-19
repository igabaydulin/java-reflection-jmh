package com.github.igabaydulin.reflection.jmh.benchmark.compile;

import com.github.igabaydulin.reflection.jmh.Library;
import org.openjdk.jmh.annotations.Benchmark;

public class Instance {

  @Benchmark
  public Library newInstance() {
    return new Library();
  }
}
