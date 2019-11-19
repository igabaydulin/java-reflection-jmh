package com.github.igabaydulin.reflection.jmh.benchmark.runtime;

import com.github.igabaydulin.reflection.jmh.state.LibraryProvider;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.openjdk.jmh.annotations.Benchmark;

public class PrivateMethodReflectionAccess {

  @Benchmark
  public Method lookup(LibraryProvider provider) throws NoSuchMethodException {
    return provider.library.getClass().getDeclaredMethod("privateMethod");
  }

  @Benchmark
  public Object invoke(LibraryProvider provider)
      throws InvocationTargetException, IllegalAccessException {
    return provider.privateMethod.invoke(provider.library);
  }
}
