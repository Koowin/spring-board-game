package com.koowin.multiplayer;


import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InterfaceTest {

  interface Test {

    int getNum();
  }

  static class TestImpl implements Test {

    private final int num;

    public TestImpl(int num) {
      this.num = num;
    }

    @Override
    public int getNum() {
      return num;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      TestImpl test = (TestImpl) o;
      return num == test.num;
    }

    @Override
    public int hashCode() {
      return num;
    }
  }

  @org.junit.jupiter.api.Test
  void equalsTest() {
    Test t1 = new TestImpl(1);
    Test t2 = new TestImpl(1);

    Assertions.assertTrue(t1.equals(t2));
  }
}
