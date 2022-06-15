package com.koowin.boardgame.piece.chess;

import com.koowin.boardgame.position.Position;

import java.util.List;

public class Knight extends AbstractOneMovePiece {
    private final static int[][] MOVES = {
            {-2, -1},
            {-2, 1},
            {-1, -2},
            {-1, 2},
            {1, -2},
            {1, 2},
            {2, -1},
            {2, 1}
    };

    public Knight(boolean isWhite) {
        super(isWhite, PieceType.KNIGHT);
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
