package com.koowin.multiplayer.dto.response;

import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.piece.PieceType;
import com.koowin.multiplayer.domain.square.Square;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PieceSetResponseDomainDto {

  private Square square;
  private PieceSetOperation operation;
  private Color color;
  private PieceType pieceType;

  @Builder
  public PieceSetResponseDomainDto(Square square,
      PieceSetOperation operation, Color color,
      PieceType pieceType) {
    this.square = square;
    this.operation = operation;
    this.color = color;
    this.pieceType = pieceType;
  }
}
