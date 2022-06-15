package com.koowin.boardgame.game.exceptions;

public class PieceCanNotMoveException extends RuntimeException{
    public PieceCanNotMoveException(String from, String to) {
        super("Piece can't move " + from + " -> " + to);
    }
}
