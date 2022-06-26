package com.koowin.multiplayer.domain.piece.pieceImpl;

import com.koowin.multiplayer.dto.request.MoveRequestDomainDto;
import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.piece.ManyMovePiece;
import com.koowin.multiplayer.domain.piece.PieceType;
import com.koowin.multiplayer.domain.square.Square;
import com.koowin.multiplayer.dto.response.PieceSetResponseDomainDto;
import com.koowin.multiplayer.exception.PieceCannotMoveException;
import java.util.List;

public class Queen extends ManyMovePiece {

  private static final int[][] DIRECTIONS = {
      {0, -1},
      {0, 1},
      {-1, 0},
      {1, 0},
      {-1, -1},
      {-1, 1},
      {1, -1},
      {1, 1}
  };

  public Queen(Color color) {
    super(PieceType.QUEEN, color);
  }

  @Override
  public boolean canMove(MoveRequestDomainDto movementDto) {
    return false;
  }

  @Override
  public List<PieceSetResponseDomainDto> move(MoveRequestDomainDto movementDto)
      throws PieceCannotMoveException {
    return null;
  }

  @Override
  public List<Square> movableSquares(Square from, Square[][] squares) {
    return movablePositionsWithDirection(from, squares, DIRECTIONS);
  }

}
