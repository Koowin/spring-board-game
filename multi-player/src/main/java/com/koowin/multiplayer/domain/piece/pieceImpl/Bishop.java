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

public class Bishop extends ManyMovePiece {

  private static final int[][] DIRECTIONS = {
      {-1, -1},
      {-1, 1},
      {1, -1},
      {1, 1}
  };

  public Bishop(Color color) {
    super(PieceType.BISHOP, color);
  }


  @Override
  public boolean canMove(MoveRequestDomainDto movementDto) {
    Square from = movementDto.getFrom();
    Square to = movementDto.getTo();

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
    return ret;
  }

  @Override
  public List<Square> movableSquares(Square from, Square[][] squares) {
    return movablePositionsWithDirection(from, squares, DIRECTIONS);
  }

}
