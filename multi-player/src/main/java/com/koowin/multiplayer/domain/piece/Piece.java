package com.koowin.multiplayer.domain.piece;

import com.koowin.multiplayer.dto.request.MoveRequestDto;
import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.position.Square;
import com.koowin.multiplayer.exception.PieceCannotMoveException;
import java.util.List;

public interface Piece {

  PieceType type();

  Color color();

  boolean canMove(MoveRequestDto movementDto);

  Piece move(MoveRequestDto movementDto) throws PieceCannotMoveException;

  List<Square> movableSquares(Square from, Square[][] squares);

}
