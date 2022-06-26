package com.koowin.multiplayer.dto.response;

import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.piece.PieceType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PieceSetResponseClientDto {

  private String square;
  private PieceSetOperation operation;
  private String pieceSymbol;

  @Builder
  public PieceSetResponseClientDto(String square,
      PieceSetOperation operation, String pieceSymbol) {
    this.square = square;
    this.operation = operation;
    this.pieceSymbol = pieceSymbol;
  }
}
