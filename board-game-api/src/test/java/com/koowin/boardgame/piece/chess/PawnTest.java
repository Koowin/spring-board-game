package com.koowin.boardgame.piece.chess;

import com.koowin.boardgame.piece.Piece;
import com.koowin.boardgame.position.Position;
import com.koowin.boardgame.position.chess.PositionImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    private Position[][] board;

    @BeforeEach
    void initBoard() {
        board = PositionImpl.initPositions();
    }

    @Test
    void Pawn_장애물_없을때() {
        Piece pawn = new Pawn(true);
        board[6][3].setPiece(Optional.of(pawn));
        List<Position> positions = pawn.movablePosition(board[6][3], board);
        System.out.println(positions);
        assertThat(positions.size()).isEqualTo(2);
        assertTrue(pawn.canMove(board[6][3], board[5][3], board));
        assertTrue(pawn.canMove(board[6][3], board[4][3], board));
    }

    @Test
    void Pawn_장애물_있을때() {
        Piece whitePawn = new Pawn(true);
        Piece blackPawn = new Pawn(false);
        board[6][3].setPiece(Optional.of(whitePawn));
        board[5][2].setPiece(Optional.of(blackPawn));
        assertTrue(whitePawn.canMove(board[6][3], board[5][2], board));
        assertTrue(blackPawn.canMove(board[5][2], board[6][3], board));
    }
}