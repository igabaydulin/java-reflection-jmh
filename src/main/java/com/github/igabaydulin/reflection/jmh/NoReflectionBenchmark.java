package com.github.igabaydulin.reflection.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

public class NoReflectionBenchmark {

  @State(Scope.Benchmark)
  public static class LibraryProvider {

    Library library;

    @Setup
    public void setUp() {
      library = new Library();
    }
  }

  @Benchmark
  public boolean methodAccess(LibraryProvider provider) {
    return provider.library.publicMethod();
  }

  @Benchmark
  public Library newInstance() {
    return new Library();
  }
}
