package com.koowin.multiplayer.domain.board;

import com.koowin.multiplayer.domain.piece.PieceType;
import com.koowin.multiplayer.domain.piece.pieceImpl.*;
import com.koowin.multiplayer.domain.position.Square;
import com.koowin.multiplayer.domain.position.SquareImpl;
import com.koowin.multiplayer.dto.request.MoveRequestClientDto;
import com.koowin.multiplayer.dto.request.MoveRequestDomainDto;
import com.koowin.multiplayer.dto.request.PeekRequestClientDto;
import com.koowin.multiplayer.dto.request.PeekRequestDomainDto;
import com.koowin.multiplayer.dto.response.PieceSetResponseClientDto;
import com.koowin.multiplayer.exception.BadSquareNameException;
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

  /**
   * a1 ~ h8 까지의 체스 칸 이름을 실제 Square 로 변환해준다.
   *
   * @param p 체스 칸 이름
   * @param squares 보드
   * @return 체스 칸 이름으로 찾은 보드 안의 특정 칸
   * @throws BadSquareNameException 올바르지 않은 이름을 입력함
   */
  private static Square parseStringToSquare(String p, Square[][] squares)
      throws BadSquareNameException {
    char[] arr = p.toCharArray();
    int row = ROW_BASE - arr[1];
    int column = arr[0] - COLUMN_BASE;

    if (row < 0 || row >= 8 || column < 0 || column >= 8) {
      throw new BadSquareNameException(p);
    }
    return squares[row][column];
  }

  /**
   * Square 로부터 a1 ~ h8 까지의 체스 칸 이름으로 변환해준다.
   *
   * @param p 특정 칸
   * @return 체스 칸 이름
   */
  private static String parseSquareToString(Square p) {
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
    Square from = parseStringToSquare(str, squares);

    if (from.getPiece() == null) {
      return new ArrayList<>();
    }
    return from.getPiece().movableSquares(from, squares).stream()
        .map(BoardImpl::parseSquareToString).collect(
            Collectors.toList());
  }

  @Override
  public List<PieceSetResponseClientDto> move(String from, String to) {

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

  /**
   * 클라이언트로부터 받은 기물 이동 명령 요청 DTO 를 도메인에서 사용할 DTO 로 변환한다.
   *
   * @param clientDto 클라이언트로부터 받은 DTO
   * @return  도메인에서 사용할 DTO
   */
  private MoveRequestDomainDto parseMoveRequest(MoveRequestClientDto clientDto) {
    String fromString = clientDto.getFrom();
    String toString = clientDto.getTo();

    Square from = parseStringToSquare(fromString, squares);
    Square to = parseStringToSquare(toString, squares);

    MoveRequestDomainDto ret = new MoveRequestDomainDto(from, to, squares);
    if (from.getPiece() != null && from.getPiece().type() == PieceType.PAWN) {
      ret.setTurnCount(turnCount);
    }
    return ret;
  }


  /**
   * 클라이언트로부터 받은 움직일 수 있는 위치 요청 DTO 를 도메인에서 사용할 DTO 로 변환한다.
   *
   * @param clientDto 클라이언트로부터 받은 DTO
   * @return 도메인에서 사용할 DTO
   */
  private PeekRequestDomainDto parsePeekRequest(PeekRequestClientDto clientDto) {
    String fromString = clientDto.getFrom();
    Square from = parseStringToSquare(fromString, squares);

    PeekRequestDomainDto ret = new PeekRequestDomainDto(from, squares);
    if (from.getPiece() != null && from.getPiece().type() == PieceType.PAWN) {
      ret.setTurnCount(turnCount);
    }
    return ret;
  }

}
