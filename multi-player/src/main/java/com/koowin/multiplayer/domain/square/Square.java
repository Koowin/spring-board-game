package com.koowin.multiplayer.domain.square;

import com.koowin.multiplayer.domain.piece.Piece;

public interface Square {

  int getRow();

  int getColumn();

  Piece getPiece();

  Piece setPiece(Piece piece);
}
