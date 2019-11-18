package com.github.igabaydulin.reflection.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class NoReflectionBenchmark {

  @State(Scope.Benchmark)
  public static class LibraryProvider {

    Library library() {
      return new Library();
    }
  }

  @Benchmark
  public void methodAccess(LibraryProvider provider, Blackhole blackhole) {
    Library library = provider.library();
    blackhole.consume(library.publicMethod());
  }

  @Benchmark
  public void newInstance(Blackhole blackhole) {
    blackhole.consume(new Library());
  }
}
