package com.koowin.multiplayer.domain.piece;

import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.position.Square;
import java.util.ArrayList;
import java.util.List;

public abstract class ManyMovePiece extends AbstractPiece {

  protected ManyMovePiece(PieceType type, Color color) {
    super(type, color);
  }

  protected List<Square> movablePositionsWithDirection(Square from, Square[][] squares,
      int[][] directions) {

    List<Square> ret = new ArrayList<>();

    for (int[] direction : directions) {
      int row = from.getRow();
      int column = from.getColumn();

      for (int i = 0; i < squares.length; i++) {
        row += direction[0];
        column += direction[1];

        if (!isValidRowColumn(row) || !isValidRowColumn(column)) {
          break;
        }

        Square next = squares[row][column];

        if (next.getPiece() == null) {
          ret.add(next);
        } else if (next.getPiece().color() == this.color) {
          break;
        } else {
          ret.add(next);
          break;
        }
      }
    }

    return ret;
  }
}
