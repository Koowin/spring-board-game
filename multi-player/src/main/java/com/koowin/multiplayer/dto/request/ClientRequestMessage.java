package com.koowin.multiplayer.dto.request;

import com.koowin.multiplayer.domain.piece.PieceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequestMessage {

  private RequestMessageType type;
  private String from;
  private String to;
  private PieceType promotionType;

  public MoveRequestClientDto toMoveDto() {
    return MoveRequestClientDto.builder()
        .from(from)
        .to(to)
        .promotionType(promotionType)
        .build();
  }

  public PeekRequestClientDto toPeekDto() {
    return new PeekRequestClientDto(from);
  }


}
