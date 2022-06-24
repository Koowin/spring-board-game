package com.koowin.multiplayer.repository;

import com.koowin.multiplayer.entity.GameRoom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class GameRoomRepository {

  private long nextId;
  private Map<Long, GameRoom> gameRoomMap;

  @PostConstruct
  private void init() {
    nextId = 1;
    gameRoomMap = new HashMap<>();
  }

  public List<GameRoom> findAll() {
    List<GameRoom> ret = new ArrayList<>(gameRoomMap.values());
    return ret;
  }

  public GameRoom create() {
    GameRoom gameRoom = new GameRoom(nextId);
    gameRoomMap.put(nextId++, gameRoom);

    return gameRoom;
  }

  public void remove(long id) {
    gameRoomMap.remove(id);
  }
}
