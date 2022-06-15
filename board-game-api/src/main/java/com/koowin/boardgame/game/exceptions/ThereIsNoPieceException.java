package com.koowin.boardgame.game.exceptions;

public class ThereIsNoPieceException extends RuntimeException{
    public ThereIsNoPieceException(String position) {
        super("There is no piece in " + position);
    }
}
