package com.koowin.boardgame.piece.chess;

import com.koowin.boardgame.piece.Piece;
import com.koowin.boardgame.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pawn extends AbstractPiece {
    private final int startRow;

    public Pawn(boolean isWhite) {
        super(isWhite, PieceType.PAWN);
        if (isWhite) {
            this.startRow = 6;
        } else {
            this.startRow = 1;
        }
    }

    @Override
    public List<Position> movablePosition(Position from, Position[][] board) {
        List<Position> ret = new ArrayList<>();
        int row = from.getRow();
        int column = from.getColumn();
        if (isWhite) {
            if (row > 0) {
                if(board[row - 1][column].peek().isEmpty()){
                    ret.add(board[row - 1][column]);
                    if (row == startRow && board[row - 2][column].peek().isEmpty()) {
                        ret.add(board[row - 2][column]);
                    }
                }
                if (column > 0) {
                    Optional<Piece> piece = board[row - 1][column - 1].peek();
                    if (piece.isPresent() && !piece.get().side()) {
                        ret.add(board[row - 1][column - 1]);
                    }
                }
                if (column < 7) {
                    Optional<Piece> piece = board[row - 1][column + 1].peek();
                    if (piece.isPresent() && !piece.get().side()) {
                        ret.add(board[row - 1][column + 1]);
                    }
                }
            }
        } else {
            if (row < 7) {
                if (board[row + 1][column].peek().isEmpty()) {
                    ret.add(board[row + 1][column]);
                    if (row == startRow && board[row + 2][column].peek().isEmpty()) {
                        ret.add(board[row + 2][column]);
                    }
                }
                if (column > 0) {
                    Optional<Piece> piece = board[row + 1][column - 1].peek();
                    if (piece.isPresent() && !piece.get().side()) {
                        ret.add(board[row + 1][column - 1]);
                    }
                }
                if (column < 7) {
                    Optional<Piece> piece = board[row + 1][column + 1].peek();
                    if (piece.isPresent() && !piece.get().side()) {
                        ret.add(board[row + 1][column + 1]);
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public boolean canMove(Position from, Position to, Position[][] board) {
        if (from.getColumn() == to.getColumn()) {
            if (isWhite) {
                return to.peek().isEmpty() && (from.getRow() - 1 == to.getRow() || (from.getRow() == 6 && to.getRow() == 4));
            } else {
                return to.peek().isEmpty() && (from.getRow() + 1 == to.getRow() || (from.getRow() == 1 && to.getRow() == 3));
            }
        }
        if (Math.abs(from.getColumn() - to.getColumn()) == 1) {
            if (isWhite) {
                return from.getRow() - 1 == to.getRow() && to.peek().isPresent() && !to.peek().get().side();
            } else {
                return from.getRow() + 1 == to.getRow() && to.peek().isPresent() && to.peek().get().side();
            }
        }
        return false;
    }
}
