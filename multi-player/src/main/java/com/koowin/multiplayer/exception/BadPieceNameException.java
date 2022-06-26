package com.koowin.multiplayer.exception;

public class BadPieceNameException extends RuntimeException {

  private static final String errorMessage = "There is no piece name : ";

  public BadPieceNameException(String pieceName) {
    super(errorMessage + pieceName);
  }
}
