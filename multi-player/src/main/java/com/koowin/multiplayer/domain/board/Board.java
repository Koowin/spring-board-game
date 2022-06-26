package com.koowin.multiplayer.domain.board;

import com.koowin.multiplayer.dto.request.MoveRequestClientDto;
import com.koowin.multiplayer.dto.response.PieceSetResponseClientDto;
import java.util.List;

public interface Board {

  List<String> movablePositions(String from);

  List<PieceSetResponseClientDto> move(MoveRequestClientDto clientDto);

  boolean isGameEnd();

  Color winner();
}
