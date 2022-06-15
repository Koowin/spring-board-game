package com.koowin.boardgame.position.chess;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.koowin.boardgame.piece.Piece;
import com.koowin.boardgame.position.Position;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequiredArgsConstructor
public class PositionImpl implements Position{
    @JsonIgnore
    private static final int BOARD_SIZE = 8;
    @JsonProperty
    private final int ROW;
    @JsonProperty
    private final int COLUMN;
    @JsonIgnore
    private Optional<Piece> piece = Optional.empty();

    public static Position[][] initPositions() {
        Position[][] board = new PositionImpl[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new PositionImpl(i, j);
            }
        }
        return board;
    }

    @Override
    public Optional<Piece> peek() {
        return piece;
    }

    @Override
    public Optional<Piece> setPiece(Optional<Piece> nextPiece) {
        Optional<Piece> prePiece = this.piece;
        this.piece = nextPiece;
        return prePiece;
    }

    @JsonIgnore
    @Override
    public int getRow() {
        return ROW;
    }

    @JsonIgnore
    @Override
    public int getColumn() {
        return COLUMN;
    }

    private static final char COLUMN_OFFSET = 'a';
    private static final char ROW_OFFSET = '8';
    @Override
    public String toString() {
        char[] ret = new char[2];
        ret[0] = (char) (COLUMN + COLUMN_OFFSET);
        ret[1] = (char) (ROW_OFFSET - ROW);
        return new String(ret);
    }
}
