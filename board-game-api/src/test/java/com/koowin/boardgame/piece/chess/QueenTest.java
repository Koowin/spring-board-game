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

class QueenTest {
    private Position[][] board;

    @BeforeEach
    void initBoard() {
        board = PositionImpl.initPositions();
    }

    @Test
    void Queen_장애물_없을때() {
        Piece queen = new Queen(true);
        board[2][2].setPiece(Optional.of(queen));
        assertThat(queen.movablePosition(board[2][2], board).size()).isEqualTo(25);
        assertTrue(queen.canMove(board[2][2], board[7][7], board));
        assertTrue(queen.canMove(board[2][2], board[2][7], board));
        assertTrue(queen.canMove(board[2][2], board[0][2], board));
    }

    @Test
    void Queen_장애물_있을때() {
        Piece whiteQueen = new Queen(true);
        Piece blackQueen = new Queen(false);
        board[2][2].setPiece(Optional.of(whiteQueen));
        board[2][4].setPiece(Optional.of(blackQueen));
        assertTrue(whiteQueen.canMove(board[2][2], board[2][4], board));
        assertFalse(whiteQueen.canMove(board[2][2], board[2][5], board));

        board[2][4].setPiece(Optional.of(whiteQueen));
        assertFalse(whiteQueen.canMove(board[2][2], board[2][4], board));
    }
}