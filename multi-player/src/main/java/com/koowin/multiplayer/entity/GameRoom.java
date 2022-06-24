package com.koowin.multiplayer.entity;

import com.koowin.multiplayer.domain.board.Board;
import com.koowin.multiplayer.domain.board.BoardImpl;
import org.springframework.web.socket.WebSocketSession;

public class GameRoom {

  private long id;
  private Board board;

  private WebSocketSession whitePlayerSession;
  private WebSocketSession blackPlayerSession;

  public GameRoom(long id) {
    this.id = id;
    board = new BoardImpl();
  }

  public void join() {

  }
}
