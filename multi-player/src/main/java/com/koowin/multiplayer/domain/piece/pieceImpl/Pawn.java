package com.koowin.multiplayer.domain.piece.pieceImpl;

import com.koowin.multiplayer.domain.piece.Piece;
import com.koowin.multiplayer.dto.request.MoveRequestDomainDto;
import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.piece.AbstractPiece;
import com.koowin.multiplayer.domain.piece.PieceType;
import com.koowin.multiplayer.domain.square.Square;
import com.koowin.multiplayer.dto.response.PieceSetOperation;
import com.koowin.multiplayer.dto.response.PieceSetResponseDomainDto;
import com.koowin.multiplayer.exception.NeedPromotionTypeException;
import com.koowin.multiplayer.exception.PieceCannotMoveException;
import com.koowin.multiplayer.exception.PromotionTypeInvalidException;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {

  private boolean isMoved = false;
  private int twoSquareMoveTurn = 0;

  public Pawn(Color color) {
    super(PieceType.PAWN, color);
  }

  @Override
  public boolean canMove(MoveRequestDomainDto movementDto) {
    return true;
  }

  @Override
  public List<PieceSetResponseDomainDto> move(MoveRequestDomainDto movementDto)
      throws PieceCannotMoveException, NeedPromotionTypeException, PromotionTypeInvalidException {

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
    List<Square> ret = new ArrayList<>();

    int row = from.getRow();
    int column = from.getColumn();

    int rowDirection;
    if (this.color == Color.WHITE) {
      rowDirection = -1;
    } else {
      rowDirection = 1;
    }

    int nextRow = row + rowDirection;
    int nextColumn = column;

    Square next = squares[nextRow][nextColumn];
    if (next.getPiece() == null) {
      ret.add(next);

      if (!isMoved) {
        nextRow += rowDirection;
        next = squares[nextRow][nextColumn];
        if (next.getPiece() == null) {
          ret.add(next);
        }
        nextRow -= rowDirection;
      }
    }

    next = squares[nextRow][nextColumn - 1];
    if (next.getPiece() != null && next.getPiece().color() != this.color) {
      ret.add(next);
    }
    next = squares[nextRow][nextColumn + 1];
    if (next.getPiece() != null && next.getPiece().color() != this.color) {
      ret.add(next);
    }

    return ret;
  }
}
