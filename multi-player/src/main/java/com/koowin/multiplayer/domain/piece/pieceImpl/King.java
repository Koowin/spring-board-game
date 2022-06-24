package com.koowin.multiplayer.domain.piece.pieceImpl;

import com.koowin.multiplayer.domain.piece.MovedChecker;
import com.koowin.multiplayer.dto.request.MoveRequestDomainDto;
import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.piece.OneMovePiece;
import com.koowin.multiplayer.domain.piece.PieceType;
import com.koowin.multiplayer.domain.position.Square;
import com.koowin.multiplayer.dto.response.PieceSetResponseDomainDto;
import com.koowin.multiplayer.exception.PieceCannotMoveException;
import java.util.ArrayList;
import java.util.List;

public class King extends OneMovePiece implements MovedChecker {

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
  private boolean isMoved = false;

  public King(Color color) {
    super(PieceType.KING, color);
  }

  @Override
  public boolean canMove(MoveRequestDomainDto movementDto) {
    Square from = movementDto.getFrom();
    Square to = movementDto.getTo();

    if (!isValidMoveRequest(movementDto)) {
      return false;
    }

    int rowDiff = Math.abs(to.getRow() - from.getRow());
    int columnDiff = Math.abs(to.getColumn() - from.getColumn());

    if (rowDiff > 1 || columnDiff > 1) {
      return false;
    }

    return true;
  }

  @Override
  public List<PieceSetResponseDomainDto> move(MoveRequestDomainDto movementDto)
      throws PieceCannotMoveException {

    if (!canMove(movementDto)) {
      throw new PieceCannotMoveException();
    }

    List<PieceSetResponseDomainDto> ret = new ArrayList<>();

    Square[][] squares = movementDto.getSquares();

    isMoved = true;
    return ret;
  }

  @Override
  public List<Square> movableSquares(Square from, Square[][] squares) {
    List<Square> ret = movablePositionWithDirection(from, squares, DIRECTIONS);

    return ret;
  }

  @Override
  public boolean isMoved() {
    return this.isMoved;
  }
}
