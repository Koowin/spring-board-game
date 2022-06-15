package com.koowin.boardgame.game.exceptions;

public class GameNotStartedException extends RuntimeException{
    public GameNotStartedException() {
        super("Game is NOT started. Start game first.");
    }
}
