package com.koowin.multiplayer.dto.request;

import com.koowin.multiplayer.domain.square.Square;
import lombok.Getter;

@Getter
public class PeekRequestDomainDto {

  private final Square from;
  private final Square[][] squares;
  private int turnCount;

  public PeekRequestDomainDto(Square from, Square[][] squares) {
    this.from = from;
    this.squares = squares;
  }

  public void setTurnCount(int turnCount) {
    this.turnCount = turnCount;
  }
}
