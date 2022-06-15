package com.koowin.boardgame.piece.chess;

import com.koowin.boardgame.position.Position;

import java.util.List;

public class Queen extends AbstractManyMovePiece {
    private static final int[][] MOVES = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1}
    };


    public Queen(boolean isWhite) {
        super(isWhite, PieceType.QUEEN);
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
        int rowDiff = to.getRow() - from.getRow();
        int columnDiff = to.getColumn() - from.getColumn();
        if (rowDiff != 0 && columnDiff != 0 && rowDiff != columnDiff && rowDiff != -columnDiff) {
            return false;
        }
        int rowAdder, columnAdder;
        if (rowDiff < 0) {
            rowAdder = -1;
        } else if (rowDiff == 0) {
            rowAdder = 0;
        } else {
            rowAdder = 1;
        }
        if (columnDiff < 0) {
            columnAdder = -1;
        } else if (columnDiff == 0) {
            columnAdder = 0;
        } else {
            columnAdder = 1;
        }
        int len = Math.max(Math.abs(rowDiff), Math.abs(columnDiff)) - 1;
        int r = from.getRow() + rowAdder;
        int c = from.getColumn() + columnAdder;
        for (int i = 0; i < len; i++) {
            if (board[r][c].peek().isPresent()) {
                return false;
            }
            r += rowAdder;
            c += columnAdder;
        }
        return true;
    }
}
