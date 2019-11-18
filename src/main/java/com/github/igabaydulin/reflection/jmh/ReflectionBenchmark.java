package com.github.igabaydulin.reflection.jmh;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class ReflectionBenchmark {

  @State(Scope.Benchmark)
  public static class LibraryProvider {

    Library library() {
      return new Library();
    }
  }

  @Benchmark
  public void methodAccess(LibraryProvider provider, Blackhole blackhole)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Library library = provider.library();
    Class<?> clazz = library.getClass();
    Method method = clazz.getDeclaredMethod("privateMethod");
    method.setAccessible(true);
    blackhole.consume(method.invoke(library));
  }

  @Benchmark
  public void newInstance(Blackhole blackhole)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    blackhole.consume(Library.class.getDeclaredConstructor().newInstance());
  }
}
