package com.koowin.multiplayer.domain.piece.pieceImpl;

import com.koowin.multiplayer.dto.request.MoveRequestDomainDto;
import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.piece.AbstractPiece;
import com.koowin.multiplayer.domain.piece.PieceType;
import com.koowin.multiplayer.domain.position.Square;
import com.koowin.multiplayer.dto.response.PieceSetResponseDomainDto;
import com.koowin.multiplayer.exception.NeedPromotionTypeException;
import com.koowin.multiplayer.exception.PieceCannotMoveException;
import com.koowin.multiplayer.exception.PromotionTypeInvalidException;
import java.util.List;

public class Pawn extends AbstractPiece {

  private boolean isMoved = false;
  private int twoSquareMoveTurn = 0;

  public Pawn(Color color) {
    super(PieceType.PAWN, color);
  }

  @Override
  public boolean canMove(MoveRequestDomainDto movementDto) {
    return false;
  }

  @Override
  public List<PieceSetResponseDomainDto> move(MoveRequestDomainDto movementDto)
      throws PieceCannotMoveException, NeedPromotionTypeException, PromotionTypeInvalidException {
    return null;
  }

  @Override
  public List<Square> movableSquares(Square from, Square[][] squares) {

    return null;
  }
}
