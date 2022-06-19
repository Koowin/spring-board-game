package com.koowin.boardgamewebsocket.socketHandler;

import com.koowin.boardgamewebsocket.exception.FullSessionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class BoardHandler extends TextWebSocketHandler {
    private static Map<WebSocketSession, Integer> sessions = new HashMap<>();
    private static int nextId = 0;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if (nextId >= 2) {
            throw new FullSessionException();
        }
        sessions.put(session, nextId);
        log.info("접속 : {}", nextId++);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        int id = sessions.get(session);
        log.info(id + " : " + message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("퇴장 : {}", sessions.get(session));
        sessions.remove(session);
    }
}
