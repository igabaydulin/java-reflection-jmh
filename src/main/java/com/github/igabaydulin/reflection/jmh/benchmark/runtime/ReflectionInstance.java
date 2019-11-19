package com.github.igabaydulin.reflection.jmh.benchmark.runtime;

import com.github.igabaydulin.reflection.jmh.Library;
import java.lang.reflect.InvocationTargetException;
import org.openjdk.jmh.annotations.Benchmark;

public class ReflectionInstance {

  @Benchmark
  public Library newInstance()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return Library.class.getDeclaredConstructor().newInstance();
  }
}
