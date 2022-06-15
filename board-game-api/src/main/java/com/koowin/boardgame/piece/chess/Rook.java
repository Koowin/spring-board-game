package com.koowin.boardgame.piece.chess;

import com.koowin.boardgame.position.Position;

import java.util.List;

public class Rook extends AbstractManyMovePiece{
    private static final int[][] MOVES = {
            {-1, 0},
            {0, -1},
            {0, 1},
            {1, 0}
    };

    public Rook(boolean isWhite) {
        super(isWhite, PieceType.ROOK);
    }

    @Override
    public List<Position> movablePosition(Position from, Position[][] board) {
        return super.movablePosition(from, board, MOVES);
    }

    @Override
    public boolean canMove(Position from, Position to, Position[][] board) {

        if (to.peek().isPresent() && to.peek().get().side() == isWhite) {
            return false;
        }
        if (from.getRow() != to.getRow() && from.getColumn() != to.getColumn()) {
            return false;
        }
        if (from.getRow() == to.getRow()) {
            int f, t;
            if (from.getColumn() < to.getColumn()) {
                f = from.getColumn() + 1;
                t = to.getColumn();
            } else {
                f = to.getColumn() + 1;
                t = from.getColumn();
            }
            for (int i = f; i < t; i++) {
                if (board[from.getRow()][i].peek().isPresent()) {
                    return false;
                }
            }
        } else {
            int f, t;
            if (from.getRow() < to.getRow()) {
                f = from.getRow() + 1;
                t = to.getRow();
            } else {
                f = to.getRow() + 1;
                t = from.getRow();
            }
            for (int i = f; i < t; i++) {
                if (board[i][from.getColumn()].peek().isPresent()) {
                    return false;
                }
            }
        }
        return true;
    }
}
