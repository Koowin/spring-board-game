package com.koowin.multiplayer.domain.piece.pieceImpl;

import com.koowin.multiplayer.dto.request.MoveRequestDomainDto;
import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.piece.ManyMovePiece;
import com.koowin.multiplayer.domain.piece.PieceType;
import com.koowin.multiplayer.domain.position.Square;
import com.koowin.multiplayer.dto.response.PieceSetResponseDomainDto;
import com.koowin.multiplayer.exception.PieceCannotMoveException;
import java.util.List;

public class Rook extends ManyMovePiece {
  private boolean isMoved = false;

  private static final int[][] DIRECTIONS = {
      {0, -1},
      {0, 1},
      {-1, 0},
      {1, 0}
  };

  public Rook(Color color) {
    super(PieceType.ROOK, color);
  }

  @Override
  public boolean canMove(MoveRequestDomainDto movementDto) {
    return false;
  }

  @Override
  public List<PieceSetResponseDomainDto> move(MoveRequestDomainDto movementDto)
      throws PieceCannotMoveException {

    isMoved = true;
    return null;
  }

  @Override
  public List<Square> movableSquares(Square from, Square[][] squares) {
    return movablePositionsWithDirection(from, squares, DIRECTIONS);
  }

  boolean isMoved() {
    return isMoved;
  }
}
