package com.koowin.multiplayer.domain.piece.pieceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.koowin.multiplayer.domain.board.Color;
import com.koowin.multiplayer.domain.piece.Piece;
import com.koowin.multiplayer.domain.square.Square;
import com.koowin.multiplayer.domain.square.SquareImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BishopTest {

  private Square[][] squares;

  public BishopTest() {
    squares = new Square[8][8];
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        squares[i][j] = new SquareImpl(i, j);
      }
    }
  }

  @BeforeEach
  void initPositions() {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        squares[i][j].setPiece(null);
      }
    }
  }

  @Test
  void movablePositionsWithNoObstacleTest() {
    Piece bishop = new Bishop(Color.BLACK);
    Square from = squares[3][4];

    List<Square> expected = new ArrayList<>();
    expected.add(squares[0][1]);
    expected.add(squares[1][2]);
    expected.add(squares[2][3]);
    expected.add(squares[2][5]);
    expected.add(squares[1][6]);
    expected.add(squares[0][7]);
    expected.add(squares[4][3]);
    expected.add(squares[5][2]);
    expected.add(squares[6][1]);
    expected.add(squares[7][0]);
    expected.add(squares[4][5]);
    expected.add(squares[5][6]);
    expected.add(squares[6][7]);

    List<Square> result = bishop.movableSquares(from, squares);

    assertThat(result.size()).isEqualTo(13);
    for (Square p : expected) {
      assertTrue(result.contains(p));
    }
  }

  @Test
  void movablePositionsWithObstacleTest() {
    Piece bishop = new Bishop(Color.WHITE);

    Piece blackObstacle = new Pawn(Color.BLACK);
    Piece whiteObstacle = new Pawn(Color.WHITE);

    Square from = squares[3][4];
    from.setPiece(bishop);
    squares[4][3].setPiece(blackObstacle);
    squares[5][6].setPiece(whiteObstacle);

    List<Square> expected = new ArrayList<>();
    expected.add(squares[0][1]);
    expected.add(squares[1][2]);
    expected.add(squares[2][3]);
    expected.add(squares[2][5]);
    expected.add(squares[1][6]);
    expected.add(squares[0][7]);
    expected.add(squares[4][3]);
    expected.add(squares[4][5]);

    List<Square> result = bishop.movableSquares(from, squares);
    assertThat(result.size()).isEqualTo(8);

    for (Square p : expected) {
      assertTrue(result.contains(p));
    }
  }

  @Test
  void canMoveTest() {

  }
}