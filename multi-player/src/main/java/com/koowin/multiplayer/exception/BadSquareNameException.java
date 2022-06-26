package com.koowin.multiplayer.exception;

public class BadSquareNameException extends RuntimeException {

  private static final String errorMessage = "Bad square name : ";

  public BadSquareNameException(String name) {
    super(errorMessage + name);
  }
}
