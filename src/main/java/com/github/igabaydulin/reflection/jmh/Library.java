package com.github.igabaydulin.reflection.jmh;

public class Library {

  private boolean privateMethod() {
    return true;
  }

  public boolean publicMethod() {
    return privateMethod();
  }
}
