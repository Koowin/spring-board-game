package com.koowin.multiplayer.domain.board;

import com.koowin.multiplayer.dto.response.PieceSetResponseDto;
import java.util.List;

public interface Board {

  List<String> movablePositions(String from);

  List<PieceSetResponseDto> move(String from, String to);

  boolean isGameEnd();

  Color winner();
}
