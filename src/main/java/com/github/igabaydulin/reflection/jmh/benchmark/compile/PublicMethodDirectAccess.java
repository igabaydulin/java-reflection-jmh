package com.github.igabaydulin.reflection.jmh.benchmark.compile;

import com.github.igabaydulin.reflection.jmh.state.LibraryProvider;
import org.openjdk.jmh.annotations.Benchmark;

public class PublicMethodDirectAccess {

  @Benchmark
  public boolean invoke(LibraryProvider provider) {
    return provider.library.publicMethod();
  }
}
