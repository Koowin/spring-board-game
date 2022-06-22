package com.koowin.multiplayer.domain.piece.pieceImpl;

import com.koowin.multiplayer.dto.request.MoveRequestDto;
import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.piece.OneMovePiece;
import com.koowin.multiplayer.domain.piece.Piece;
import com.koowin.multiplayer.domain.piece.PieceType;
import com.koowin.multiplayer.domain.position.Square;
import com.koowin.multiplayer.exception.PieceCannotMoveException;
import java.util.List;

public class Knight extends OneMovePiece {

  private static final int[][] DIRECTIONS = {
      {-2, 1},
      {-1, 2},
      {1, 2},
      {2, 1},
      {2, -1},
      {1, -2},
      {-1, -2},
      {-2, -1}
  };

  public Knight(Color color) {
    super(PieceType.KNIGHT, color);
  }

  @Override
  public boolean canMove(MoveRequestDto movementDto) {
    return false;
  }

  @Override
  public Piece move(MoveRequestDto movementDto)
      throws PieceCannotMoveException {
    return null;
  }

  @Override
  public List<Square> movableSquares(Square from, Square[][] squares) {
    return movablePositionWithDirection(from, squares, DIRECTIONS);
  }

}
