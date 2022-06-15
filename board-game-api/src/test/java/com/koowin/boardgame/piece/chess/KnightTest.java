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

class KnightTest {
    private Position[][] board;

    @BeforeEach
    void initBoard() {
        board = PositionImpl.initPositions();
    }

    @Test
    void Knight_장애물_없을때() {
        Piece knight = new Knight(true);
        board[2][2].setPiece(Optional.of(knight));
        assertTrue(knight.canMove(board[2][2], board[0][1], board));
        assertTrue(knight.canMove(board[2][2], board[0][3], board));
        assertTrue(knight.canMove(board[2][2], board[1][0], board));
        assertTrue(knight.canMove(board[2][2], board[1][4], board));
        assertTrue(knight.canMove(board[2][2], board[3][0], board));
        assertTrue(knight.canMove(board[2][2], board[3][4], board));
        assertTrue(knight.canMove(board[2][2], board[4][1], board));
        assertTrue(knight.canMove(board[2][2], board[4][3], board));
    }

    @Test
    void Knight_장애물_있을때() {
        Piece whiteKnight = new Knight(true);
        Piece blackKnight = new Knight(false);
        board[2][2].setPiece(Optional.of(whiteKnight));
        board[0][1].setPiece(Optional.of(blackKnight));
        assertTrue(whiteKnight.canMove(board[2][2], board[0][1], board));

        board[0][1].setPiece(Optional.of(whiteKnight));
        assertFalse(whiteKnight.canMove(board[2][2], board[0][1], board));
    }
}