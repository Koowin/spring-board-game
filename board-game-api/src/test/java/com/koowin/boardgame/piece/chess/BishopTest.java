package com.koowin.boardgame.piece.chess;

import com.koowin.boardgame.piece.Piece;
import com.koowin.boardgame.position.Position;
import com.koowin.boardgame.position.chess.PositionImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BishopTest {
    private Position[][] board;

    @BeforeEach
    void initBoard() {
        board = PositionImpl.initPositions();
    }

    @Test
    void Bishop_장애물_없을때() {
        Piece bishop = new Bishop(true);
        board[2][2].setPiece(Optional.of(bishop));
        assertThat(bishop.movablePosition(board[2][2], board).size()).isEqualTo(11);

        assertTrue(bishop.canMove(board[2][2], board[7][7], board));
        assertFalse(bishop.canMove(board[2][2], board[4][7], board));
    }

    @Test
    void Bishop_장애물_있을때() {
        Piece whiteBishop = new Bishop(true);
        Piece blackBishop = new Bishop(false);

        board[2][2].setPiece(Optional.of(whiteBishop));
        board[3][3].setPiece(Optional.of(blackBishop));

        assertTrue(whiteBishop.canMove(board[2][2], board[3][3], board));
        assertFalse(whiteBishop.canMove(board[2][2], board[4][4], board));

        board[3][3].setPiece(Optional.of(whiteBishop));

        assertFalse(whiteBishop.canMove(board[2][2], board[3][3], board));
    }
}