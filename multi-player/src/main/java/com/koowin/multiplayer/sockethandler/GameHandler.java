package com.koowin.multiplayer.sockethandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koowin.multiplayer.domain.board.Board;
import com.koowin.multiplayer.domain.board.BoardImpl;
import com.koowin.multiplayer.dto.request.ClientRequestMessage;
import com.koowin.multiplayer.dto.request.MoveRequestClientDto;
import com.koowin.multiplayer.dto.request.RequestMessageType;
import com.koowin.multiplayer.dto.request.PeekRequestClientDto;
import com.koowin.multiplayer.dto.response.ClientResponseMessage;
import com.koowin.multiplayer.dto.response.PieceSetResponseClientDto;
import com.koowin.multiplayer.dto.response.ResponseMessageType;
import com.koowin.multiplayer.repository.GameRoomRepository;
import java.util.List;
import java.util.Map;
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
  private Board board = new BoardImpl();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    log.info("Connected: " + session);
    Map<String, Object> attributes = session.getAttributes();
    System.out.println(attributes);

    System.out.println(attributes.get("uid") + " " + attributes.get("uid").getClass());
    System.out.println(attributes.get("gameId"));
    super.afterConnectionEstablished(session);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    String msg = message.getPayload();
    ClientRequestMessage clientRequestMessage = objectMapper.readValue(msg,
        ClientRequestMessage.class);
    log.info(msg);
    if (clientRequestMessage.getType() == RequestMessageType.PEEK) {
      PeekRequestClientDto peekRequestClientDto = clientRequestMessage.toPeekDto();

      List<String> list = board.movablePositions(peekRequestClientDto.getFrom());
      ClientResponseMessage clientResponseMessage = ClientResponseMessage.builder()
          .type(ResponseMessageType.MARK)
          .marks(list)
          .build();

      TextMessage textMessage = new TextMessage(
          objectMapper.writeValueAsString(clientResponseMessage));
      session.sendMessage(textMessage);
    } else {
      MoveRequestClientDto moveRequestClientDto = clientRequestMessage.toMoveDto();

      List<PieceSetResponseClientDto> modifies = board.move(moveRequestClientDto);
      ClientResponseMessage clientResponseMessage = ClientResponseMessage.builder()
          .type(ResponseMessageType.MODIFY)
          .modifies(modifies)
          .build();

      TextMessage textMessage = new TextMessage(
          objectMapper.writeValueAsString(clientResponseMessage));
      session.sendMessage(textMessage);
    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    log.info("Disconnected: " + session);
    super.afterConnectionClosed(session, status);
  }
}
