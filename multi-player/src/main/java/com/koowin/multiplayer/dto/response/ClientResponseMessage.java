package com.koowin.multiplayer.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponseMessage {
  private ResponseMessageType type;
  private List<String> marks;
  private List<PieceSetResponseClientDto> modifies;

  @Builder
  public ClientResponseMessage(ResponseMessageType type, List<String> marks,
      List<PieceSetResponseClientDto> modifies) {
    this.type = type;
    this.marks = marks;
    this.modifies = modifies;
  }
}
