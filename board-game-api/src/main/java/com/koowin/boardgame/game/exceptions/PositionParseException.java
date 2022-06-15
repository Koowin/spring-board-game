package com.koowin.boardgame.game.exceptions;

public class PositionParseException extends RuntimeException{
    public PositionParseException(String position) {
        super("INVALID position: " + position);
    }
}
