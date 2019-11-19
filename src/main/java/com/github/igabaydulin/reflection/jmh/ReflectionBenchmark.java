package com.github.igabaydulin.reflection.jmh;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

public class ReflectionBenchmark {

  @State(Scope.Benchmark)
  public static class LibraryProvider {

    Library library;
    Method privateMethod;
    Method publicMethod;

    @Setup
    public void setUp() {
      library = new Library();
      try {
        privateMethod = Library.class.getDeclaredMethod("privateMethod");
        publicMethod = Library.class.getDeclaredMethod("publicMethod");
      } catch (NoSuchMethodException e) {
        throw new IllegalStateException(e);
      }
      privateMethod.setAccessible(true);
      publicMethod.setAccessible(true);
    }
  }

  @Benchmark
  public Object privateMethodAccess(LibraryProvider provider)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Library library = provider.library;
    Class<?> clazz = library.getClass();
    Method method = clazz.getDeclaredMethod("privateMethod");
    method.setAccessible(true);
    return method.invoke(library);
  }

  @Benchmark
  public Object privateCachedMethodAccess(LibraryProvider provider)
      throws InvocationTargetException, IllegalAccessException {
    return provider.privateMethod.invoke(provider.library);
  }

  @Benchmark
  public Object publicCachedMethodAccess(LibraryProvider provider)
      throws InvocationTargetException, IllegalAccessException {
    return provider.publicMethod.invoke(provider.library);
  }

  @Benchmark
  public Object newInstance()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return Library.class.getDeclaredConstructor().newInstance();
  }
}
