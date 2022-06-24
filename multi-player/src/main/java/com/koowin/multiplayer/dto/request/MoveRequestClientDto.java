package com.koowin.multiplayer.dto.request;

import com.koowin.multiplayer.domain.piece.PieceType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoveRequestClientDto {

  private String from;
  private String to;
  private PieceType promotionType;

  @Builder
  public MoveRequestClientDto(String from, String to, PieceType promotionType) {
    this.from = from;
    this.to = to;
    this.promotionType = promotionType;
  }
}
