package com.koowin.multiplayer.dto.request;

import com.koowin.multiplayer.domain.piece.PieceType;
import com.koowin.multiplayer.domain.position.Square;
import lombok.Getter;

@Getter
public class MoveRequestDomainDto {
  private final Square from;
  private final Square to;
  private final Square[][] squares;
  private int turnCount;
  private PieceType promotionType;

  public MoveRequestDomainDto(Square from, Square to, Square[][] squares) {
    this.from = from;
    this.to = to;
    this.squares = squares;
  }

  public void setTurnCount(int turnCount) {
    this.turnCount = turnCount;
  }

  public void setPromotionType(PieceType promotionType) {
    this.promotionType = promotionType;
  }
}
