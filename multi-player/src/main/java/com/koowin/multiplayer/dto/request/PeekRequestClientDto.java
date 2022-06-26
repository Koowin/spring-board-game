package com.koowin.multiplayer.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeekRequestClientDto {

  private String from;

  public PeekRequestClientDto(String from) {
    this.from = from;
  }
}
