package com.koowin.multiplayer.sockethandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koowin.multiplayer.domain.board.Board;
import com.koowin.multiplayer.domain.board.BoardImpl;
import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.piece.PieceType;
import com.koowin.multiplayer.dto.request.ClientMessage;
import com.koowin.multiplayer.dto.request.MessageType;
import com.koowin.multiplayer.dto.request.PeekRequestClientDto;
import com.koowin.multiplayer.dto.response.PieceSetOperation;
import com.koowin.multiplayer.dto.response.PieceSetResponseClientDto;
import com.koowin.multiplayer.repository.GameRoomRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class GameHandler extends TextWebSocketHandler {

  private final GameRoomRepository gameRoomRepository;
  private final ObjectMapper objectMapper;

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    log.info("Connected: " + session);
    super.afterConnectionEstablished(session);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    String msg = message.getPayload();
    ClientMessage clientMessage = objectMapper.readValue(msg, ClientMessage.class);
    log.info(msg);
    if (clientMessage.getType() == MessageType.PEEK) {
      PeekRequestClientDto peekRequestClientDto = clientMessage.toPeekDto();
      log.info("From client : " + peekRequestClientDto.getFrom());

      List<PieceSetResponseClientDto> list = new ArrayList<>();
      list.add(PieceSetResponseClientDto.builder()
          .operation(PieceSetOperation.SET)
          .square("a8")
          .pieceSymbol("♙")
          .build());
      list.add(PieceSetResponseClientDto.builder()
          .operation(PieceSetOperation.SET)
          .square("a7")
              .pieceSymbol("♚")
          .build());
      TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(list));
      session.sendMessage(textMessage);
    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    log.info("Disconnected: " + session);
    super.afterConnectionClosed(session, status);
  }
}
