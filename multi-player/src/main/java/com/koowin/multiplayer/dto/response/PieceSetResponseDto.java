package com.koowin.multiplayer.dto.response;

import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.piece.PieceType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PieceSetResponseDto {
  private String position;
  private Operation operation;
  private Color color;
  private PieceType pieceType;

  @Builder
  public PieceSetResponseDto(String position,
      Operation operation, Color color, PieceType pieceType) {
    this.position = position;
    this.operation = operation;
    this.color = color;
    this.pieceType = pieceType;
  }

  enum Operation {
    DELETE, SET
  }
}
