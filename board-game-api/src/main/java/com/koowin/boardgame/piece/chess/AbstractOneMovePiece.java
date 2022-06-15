package com.koowin.boardgame.piece.chess;

import com.koowin.boardgame.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOneMovePiece extends AbstractPiece{
    public AbstractOneMovePiece(boolean isWhite, PieceType type) {
        super(isWhite, type);
    }

    List<Position> movablePosition(Position from, Position[][] board, int[][] MOVES){
        List<Position> ret = new ArrayList<>();

        int row = from.getRow();
        int column = from.getColumn();
        for (int[] move : MOVES) {
            int nextRow = row + move[0];
            int nextColumn = column + move[1];

            if (nextRow < 0 || nextRow >= board.length || nextColumn < 0 || nextColumn >= board.length) {
                continue;
            }
            Position to = board[nextRow][nextColumn];
            if (to.peek().isEmpty() || to.peek().get().side() != isWhite) {
                ret.add(to);
            }
        }
        return ret;
    }
}
