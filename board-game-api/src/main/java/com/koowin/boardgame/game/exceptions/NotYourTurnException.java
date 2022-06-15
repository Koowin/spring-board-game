package com.koowin.boardgame.game.exceptions;

public class NotYourTurnException extends RuntimeException{
    public NotYourTurnException() {
        super("Not your turn.");
    }
}
