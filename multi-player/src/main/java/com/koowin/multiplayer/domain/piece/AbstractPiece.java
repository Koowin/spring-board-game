package com.koowin.multiplayer.domain.piece;

import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.position.Square;
import com.koowin.multiplayer.dto.request.MoveRequestDomainDto;

public abstract class AbstractPiece implements Piece {

  protected PieceType type;
  protected Color color;

  protected AbstractPiece(PieceType type, Color color) {
    this.type = type;
    this.color = color;
  }

  @Override
  public PieceType type() {
    return this.type;
  }

  @Override
  public Color color() {
    return this.color;
  }

  protected static boolean isValidRowColumn(int rc) {
    return 0 <= rc && rc < 8;
  }

  protected static boolean isValidMoveRequest(MoveRequestDomainDto request) {
    Square from = request.getFrom();
    Square to = request.getTo();

    if (from.equals(to)) {
      return false;
    }

    Piece fromPiece = from.getPiece();
    Piece toPiece = to.getPiece();

    if (fromPiece == null) {
      return false;
    }

    if (toPiece != null && toPiece.color() == fromPiece.color()) {
      return false;
    }

    return true;
  }
}
