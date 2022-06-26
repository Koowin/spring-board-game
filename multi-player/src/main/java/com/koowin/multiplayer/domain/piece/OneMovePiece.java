package com.koowin.multiplayer.domain.piece;

import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.square.Square;
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

      if (!isValidRowColumn(row) || !isValidRowColumn(column)) {
        continue;
      }

      Square next = squares[row][column];

      if (next.getPiece() == null || next.getPiece().color() != color) {
        ret.add(next);
      }
    }

    return ret;
  }
}
