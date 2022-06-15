package com.koowin.boardgame.game.exceptions;

public class GameEndException extends RuntimeException{
    public GameEndException() {
        super("Game already end. Start new game.");
    }
}
