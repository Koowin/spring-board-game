package com.koowin.boardgame.game.exceptions;

public class GameNotEndException extends RuntimeException{
    public GameNotEndException() {
        super("Game is not end. There is no Result");
    }
}
