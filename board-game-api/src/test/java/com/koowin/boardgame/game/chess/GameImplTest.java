package com.koowin.boardgame.game.chess;

import com.koowin.boardgame.game.*;
import com.koowin.boardgame.game.exceptions.*;
import com.koowin.boardgame.piece.*;
import com.koowin.boardgame.piece.chess.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameImplTest {
    Game game = new GameImpl();

    @BeforeEach
    void initGame() {
        game.startNewGame();
    }

    @Test
    void 기물이_제자리에_있는가() {
        Piece whiteKing = new King(true);
        Piece whiteQueen = new Queen(true);
        Piece whiteBishop = new Bishop(true);
        Piece whiteKnight = new Knight(true);
        Piece whiteRook = new Rook(true);
        Piece whitePawn = new Pawn(true);

        Piece blackKing = new King(false);
        Piece blackQueen = new Queen(false);
        Piece blackBishop = new Bishop(false);
        Piece blackKnight = new Knight(false);
        Piece blackRook = new Rook(false);
        Piece blackPawn = new Pawn(false);

        assertThat(game.peek("d8").get()).isEqualTo(blackKing);
        assertThat(game.peek("e8").get()).isEqualTo(blackQueen);
        assertThat(game.peek("c8").get()).isEqualTo(blackBishop);
        assertThat(game.peek("f8").get()).isEqualTo(blackBishop);
        assertThat(game.peek("b8").get()).isEqualTo(blackKnight);
        assertThat(game.peek("g8").get()).isEqualTo(blackKnight);
        assertThat(game.peek("a8").get()).isEqualTo(blackRook);
        assertThat(game.peek("h8").get()).isEqualTo(blackRook);

        assertThat(game.peek("d1").get()).isEqualTo(whiteKing);
        assertThat(game.peek("e1").get()).isEqualTo(whiteQueen);
        assertThat(game.peek("c1").get()).isEqualTo(whiteBishop);
        assertThat(game.peek("f1").get()).isEqualTo(whiteBishop);
        assertThat(game.peek("b1").get()).isEqualTo(whiteKnight);
        assertThat(game.peek("g1").get()).isEqualTo(whiteKnight);
        assertThat(game.peek("a1").get()).isEqualTo(whiteRook);
        assertThat(game.peek("h1").get()).isEqualTo(whiteRook);

        for (char c = 'a'; c <= 'h'; c++) {
            String blackPawnPosition = c + "7";
            String whitePawnPosition = c + "2";
            assertThat(game.peek(blackPawnPosition).get()).isEqualTo(blackPawn);
            assertThat(game.peek(whitePawnPosition).get()).isEqualTo(whitePawn);
        }
    }

    @Test
    void 본인의_차례에만_움직일_수_있는가() {
        assertThrows(NotYourTurnException.class, () -> game.move("b8", "a6"));
        game.move("b2", "b4");
        assertThrows(NotYourTurnException.class, () -> game.move("g1", "h3"));
    }
}