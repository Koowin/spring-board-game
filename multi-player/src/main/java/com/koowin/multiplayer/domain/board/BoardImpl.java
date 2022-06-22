package com.koowin.multiplayer.domain.board;

import com.koowin.multiplayer.domain.piece.pieceImpl.*;
import com.koowin.multiplayer.domain.position.Square;
import com.koowin.multiplayer.domain.position.SquareImpl;
import com.koowin.multiplayer.dto.response.PieceSetResponseDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BoardImpl implements Board {

  private Square[][] squares;

  private Set<Square> whitePieceSquares;
  private Set<Square> blackPieceSquares;

  private Square whiteKingSquare;
  private Square blackKingSquare;

  private int turnCount = 1;

  private static final char ROW_BASE = '8';
  private static final char COLUMN_BASE = 'a';

  private static Square parseStringToPosition(String p, Square[][] squares) {
    char[] arr = p.toCharArray();
    int row = ROW_BASE - arr[1];
    int column = arr[0] - COLUMN_BASE;

    return squares[row][column];
  }

  private static String parsePositionToString(Square p) {
    StringBuilder stringBuilder = new StringBuilder();
    int row = p.getRow();
    int column = p.getColumn();

    stringBuilder.append((char) (COLUMN_BASE + column));
    stringBuilder.append((char) (ROW_BASE - row));

    return stringBuilder.toString();
  }

  public BoardImpl() {
    initPositions();
    initPieces();
  }

  private void initPositions() {
    int BOARD_SIZE = 8;
    squares = new Square[BOARD_SIZE][BOARD_SIZE];

    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        squares[i][j] = new SquareImpl(i, j);
      }
    }
  }

  private void initPieces() {
    squares[0][0].setPiece(new Rook(Color.BLACK));
    squares[0][7].setPiece(new Rook(Color.BLACK));
    squares[0][1].setPiece(new Knight(Color.BLACK));
    squares[0][6].setPiece(new Knight(Color.BLACK));
    squares[0][2].setPiece(new Bishop(Color.BLACK));
    squares[0][5].setPiece(new Bishop(Color.BLACK));
    squares[0][3].setPiece(new Queen(Color.BLACK));
    squares[0][4].setPiece(new King(Color.BLACK));

    squares[7][0].setPiece(new Rook(Color.WHITE));
    squares[7][7].setPiece(new Rook(Color.WHITE));
    squares[7][1].setPiece(new Knight(Color.WHITE));
    squares[7][6].setPiece(new Knight(Color.WHITE));
    squares[7][2].setPiece(new Bishop(Color.WHITE));
    squares[7][5].setPiece(new Bishop(Color.WHITE));
    squares[7][3].setPiece(new Queen(Color.WHITE));
    squares[7][4].setPiece(new King(Color.WHITE));

    for (int i = 0; i < squares.length; i++) {
      squares[1][i].setPiece(new Pawn(Color.BLACK));
      squares[6][i].setPiece(new Pawn(Color.WHITE));
    }

    int positionsSize = 64;
    whitePieceSquares = new HashSet<>(positionsSize);
    blackPieceSquares = new HashSet<>(positionsSize);

    for (int i = 0; i < squares.length; i++) {
      whitePieceSquares.add(squares[0][i]);
      whitePieceSquares.add(squares[1][i]);
      blackPieceSquares.add(squares[6][i]);
      blackPieceSquares.add(squares[7][i]);
    }

    whiteKingSquare = squares[7][4];
    blackKingSquare = squares[0][4];
  }

  @Override
  public List<String> movablePositions(String str) {
    Square from = parseStringToPosition(str, squares);

    if (from.peek() == null) {
      return new ArrayList<>();
    }
    return from.peek().movableSquares(from, squares).stream()
        .map(BoardImpl::parsePositionToString).collect(
            Collectors.toList());
  }

  @Override
  public List<PieceSetResponseDto> move(String from, String to) {

    return null;
  }

  @Override
  public boolean isGameEnd() {
    return false;
  }

  @Override
  public Color winner() {
    return null;
  }
}
