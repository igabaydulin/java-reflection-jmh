package com.github.igabaydulin.reflection.jmh.state;

import com.github.igabaydulin.reflection.jmh.Library;
import java.lang.reflect.Method;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class LibraryProvider {

  public Library library;
  public Method privateMethod;
  public Method publicMethod;

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
