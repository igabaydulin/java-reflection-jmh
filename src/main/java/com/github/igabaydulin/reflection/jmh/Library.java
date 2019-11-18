package com.github.igabaydulin.reflection.jmh;

class Library {

  private boolean privateMethod() {
    return true;
  }

  boolean publicMethod() {
    return privateMethod();
  }
}
