package com.koowin.boardgame.piece.chess;

import com.koowin.boardgame.position.Position;

import java.util.List;

public class Bishop extends AbstractManyMovePiece{
    private static final int[][] MOVES = {
            {-1, -1},
            {-1, 1},
            {1, -1},
            {1, 1}
    };

    public Bishop(boolean isWhite) {
        super(isWhite, PieceType.BISHOP);
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
        if (Math.abs(rowDiff) != Math.abs(columnDiff)) {
            return false;
        }

        int rowAdder = rowDiff < 0 ? -1 : 1;
        int columnAdder = columnDiff < 0 ? -1 : 1;

        int nextRow = from.getRow() + rowAdder;
        int nextColumn = from.getColumn() + columnAdder;

        for (int i = 1; i < Math.abs(rowDiff); i++) {
            if (board[nextRow][nextColumn].peek().isPresent()) {
                return false;
            }
            nextRow += rowAdder;
            nextColumn += columnAdder;
        }
        return true;
    }
}
