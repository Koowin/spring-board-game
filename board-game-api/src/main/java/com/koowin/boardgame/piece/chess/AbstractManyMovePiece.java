package com.koowin.boardgame.piece.chess;

import com.koowin.boardgame.piece.Piece;
import com.koowin.boardgame.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractManyMovePiece extends AbstractPiece{
    public AbstractManyMovePiece(boolean isWhite, PieceType type) {
        super(isWhite, type);
    }

    List<Position> movablePosition(Position from, Position[][] board, int[][] MOVES) {
        List<Position> ret = new ArrayList<>();
        int row = from.getRow();
        int column = from.getColumn();

        for (int[] move : MOVES) {
            int nextRow = row + move[0];
            int nextColumn = column + move[1];
            for (int i = 0; i < 7; i++) {
                if(nextRow < 0 || nextRow >= board.length || nextColumn < 0 || nextColumn >= board.length){
                    break;
                }
                Position nextPosition = board[nextRow][nextColumn];
                Optional<Piece> piece = nextPosition.peek();
                if (piece.isPresent()) {
                    if (piece.get().side() != isWhite) {
                        ret.add(nextPosition);
                    }
                    break;
                }
                ret.add(nextPosition);
                nextRow += move[0];
                nextColumn += move[1];
            }
        }
        return ret;
    }
}
