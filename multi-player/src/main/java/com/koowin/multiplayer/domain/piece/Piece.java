package com.koowin.multiplayer.domain.piece;

import com.koowin.multiplayer.dto.request.MoveRequestDomainDto;
import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.position.Square;
import com.koowin.multiplayer.dto.response.PieceSetResponseDomainDto;
import com.koowin.multiplayer.exception.PieceCannotMoveException;
import java.util.List;

public interface Piece {

  PieceType type();

  Color color();

  boolean canMove(MoveRequestDomainDto movementDto);

  List<PieceSetResponseDomainDto> move(MoveRequestDomainDto movementDto) throws PieceCannotMoveException;

  List<Square> movableSquares(Square from, Square[][] squares);
}
