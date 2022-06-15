package com.koowin.boardgame.piece.chess;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.koowin.boardgame.piece.Piece;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RequiredArgsConstructor
public abstract class AbstractPiece implements Piece {
    final boolean isWhite;
    final PieceType type;

    @Override
    public boolean side() {
        return isWhite;
    }

    @Override
    public String toString() {
        return "AbstractPiece{" +
                "isWhite=" + isWhite +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean isKing() {
        return type == PieceType.KING;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPiece that = (AbstractPiece) o;
        return isWhite == that.isWhite && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isWhite, type);
    }
}
