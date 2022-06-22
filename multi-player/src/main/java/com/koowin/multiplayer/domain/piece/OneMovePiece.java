package com.koowin.multiplayer.domain.piece;

import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.position.Square;
import java.util.ArrayList;
import java.util.List;

public abstract class OneMovePiece extends AbstractPiece {

  protected OneMovePiece(PieceType type, Color color) {
    super(type, color);
  }

  protected List<Square> movablePositionWithDirection(Square from, Square[][] squares,
      int[][] directions) {
    List<Square> ret = new ArrayList<>();

    for (int[] direction : directions) {
      int row = from.getRow() + direction[0];
      int column = from.getColumn() + direction[1];

      if (!isValid(row) || !isValid(column)) {
        continue;
      }

      Square next = squares[row][column];

      if (next.peek() == null || next.peek().color() != color) {
        ret.add(next);
      }
    }

    return ret;
  }
}
