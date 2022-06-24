package com.koowin.multiplayer.domain.piece;

import com.koowin.multiplayer.exception.BadPieceNameException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PieceType {
  KING("KING"), QUEEN("QUEEN"), ROOK("ROOK"), BISHOP("BISHOP"), KNIGHT("KNIGHT"), PAWN("PAWN");

  private final String pieceName;

  PieceType(String pieceName) {
    this.pieceName = pieceName;
  }

  private static final Map<String, PieceType> stringToEnum = Stream.of(values()).collect(
      Collectors.toMap(Object::toString, e -> e));

  public static PieceType fromString(String pieceName) throws BadPieceNameException {
    PieceType ret = stringToEnum.get(pieceName);
    if (ret == null) {
      throw new BadPieceNameException(pieceName);
    }
    return ret;
  }

  @Override
  public String toString() {
    return pieceName;
  }
}
