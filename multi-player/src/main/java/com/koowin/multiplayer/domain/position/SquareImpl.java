package com.koowin.multiplayer.domain.position;

import com.koowin.multiplayer.domain.piece.Piece;

public class SquareImpl implements Square {

  private final int row;
  private final int column;
  private Piece piece;
  private final int hashcode;

  public SquareImpl(int row, int column) {
    this.row = row;
    this.column = column;
    hashcode = row << 3 + column;
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public int getColumn() {
    return column;
  }

  @Override
  public Piece peek() {
    return piece;
  }

  @Override
  public Piece setPiece(Piece piece) {
    return this.piece = piece;
  }

  @Override
  public int hashCode() {
    return this.hashcode;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Square)) {
      return false;
    }
    SquareImpl p = (SquareImpl) obj;
    return this.row == p.row && this.column == p.column;
  }

  @Override
  public String toString() {
    return "Square{" +
        "row=" + row +
        ", column=" + column +
        '}';
  }
}
