package com.koowin.boardgame.game.exceptions;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(String gameType) {
        super("Could not find game " + gameType);
    }
}
