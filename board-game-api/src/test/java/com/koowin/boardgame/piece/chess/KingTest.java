package com.koowin.boardgame.piece.chess;

import com.koowin.boardgame.game.chess.GameImpl;
import com.koowin.boardgame.game.exceptions.PieceCanNotMoveException;
import com.koowin.boardgame.piece.Piece;
import com.koowin.boardgame.position.Position;
import com.koowin.boardgame.position.chess.PositionImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class KingTest {
    private Position[][] board;

    @BeforeEach
    void initGame() {
        board = PositionImpl.initPositions();
    }

    @Test
    void King_장애물_없을때() {
        Piece king = new King(true);
        board[1][1].setPiece(Optional.of(king));

        assertThat(board[1][1].peek().get()).isEqualTo(king);
        List<Position> positions = king.movablePosition(board[1][1], board);
        assertThat(positions.size()).isEqualTo(8);
    }

    @Test
    void King_장애물_있을때() {
        Piece whiteKing = new King(true);
        Piece blackKing = new King(false);

        board[1][1].setPiece(Optional.of(whiteKing));
        board[1][2].setPiece(Optional.of(blackKing));
        assertThat(whiteKing.movablePosition(board[1][1], board).size()).isEqualTo(8);

        board[1][2].setPiece(Optional.of(whiteKing));
        assertThat(whiteKing.movablePosition(board[1][1], board).size()).isEqualTo(7);
        assertFalse(whiteKing.canMove(board[1][1], board[1][2], board));
    }
}