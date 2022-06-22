package com.koowin.multiplayer.domain.position;

import com.koowin.multiplayer.domain.piece.Piece;

public interface Square {

  int getRow();

  int getColumn();

  Piece peek();

  Piece setPiece(Piece piece);
}
