package com.koowin.multiplayer.domain.piece;

import com.koowin.multiplayer.domain.board.Color;

public abstract class AbstractPiece implements Piece {

  PieceType type;
  Color color;

  protected AbstractPiece(PieceType type, Color color) {
    this.type = type;
    this.color = color;
  }

  @Override
  public PieceType type() {
    return this.type;
  }

  @Override
  public Color color() {
    return this.color;
  }

  protected static boolean isValid(int rc) {
    return 0 <= rc && rc < 8;
  }
}
