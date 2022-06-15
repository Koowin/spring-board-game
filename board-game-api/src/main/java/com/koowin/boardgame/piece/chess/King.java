package com.koowin.boardgame.piece.chess;

import com.koowin.boardgame.position.Position;

import java.util.List;

public class King extends AbstractOneMovePiece{
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

    public King(boolean isWhite) {
        super(isWhite, PieceType.KING);
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

        for (int[] move : MOVES) {
            if (from.getRow() + move[0] == to.getRow() && from.getColumn() + move[1] == to.getColumn()) {
                return true;
            }
        }
        return false;
    }
}
