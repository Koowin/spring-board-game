package com.koowin.multiplayer.domain.piece.pieceImpl;

import com.koowin.multiplayer.dto.request.MoveRequestDomainDto;
import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.piece.ManyMovePiece;
import com.koowin.multiplayer.domain.piece.PieceType;
import com.koowin.multiplayer.domain.square.Square;
import com.koowin.multiplayer.dto.response.PieceSetOperation;
import com.koowin.multiplayer.dto.response.PieceSetResponseDomainDto;
import com.koowin.multiplayer.exception.PieceCannotMoveException;
import java.util.ArrayList;
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
    return true;
  }

  @Override
  public List<PieceSetResponseDomainDto> move(MoveRequestDomainDto movementDto)
      throws PieceCannotMoveException {
    List<PieceSetResponseDomainDto> ret = new ArrayList<>();

    if (!canMove(movementDto)) {
      return ret;
    }

    movementDto.getFrom().setPiece(null);
    movementDto.getTo().setPiece(this);

    ret.add(PieceSetResponseDomainDto.builder()
        .operation(PieceSetOperation.DELETE)
        .square(movementDto.getFrom())
        .build()
    );

    ret.add(PieceSetResponseDomainDto.builder()
        .operation(PieceSetOperation.SET)
        .square(movementDto.getTo())
        .color(color)
        .pieceType(type)
        .build());

    isMoved = true;
    return ret;
  }

  @Override
  public List<Square> movableSquares(Square from, Square[][] squares) {
    return movablePositionsWithDirection(from, squares, DIRECTIONS);
  }

  boolean isMoved() {
    return isMoved;
  }
}
