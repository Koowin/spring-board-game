package com.koowin.boardgame.game.chess;

import com.koowin.boardgame.game.Game;
import com.koowin.boardgame.game.GameResult;
import com.koowin.boardgame.game.exceptions.NotYourTurnException;
import com.koowin.boardgame.game.exceptions.PieceCanNotMoveException;
import com.koowin.boardgame.game.exceptions.PositionParseException;
import com.koowin.boardgame.game.exceptions.ThereIsNoPieceException;
import com.koowin.boardgame.piece.Piece;
import com.koowin.boardgame.piece.chess.*;
import com.koowin.boardgame.position.Position;
import com.koowin.boardgame.position.chess.PositionImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("chess")
public class GameImpl implements Game {
    private Position[][] board;
    private boolean isEnd;
    private boolean isWhiteWin;
    private boolean isWhiteTurn;

    @Override
    public void startNewGame() {
        initBoard();
        setPiecesDefault();
        isEnd = false;
        isWhiteTurn = true;
    }

    public void initBoard() {
        board = PositionImpl.initPositions();
    }

    @Override
    public Optional<Piece> peek(String position) {
        return parsePosition(position, board).peek();
    }

    private static final char ROW_OFFSET = 'a';
    private static final char COLUMN_OFFSET = '8';

    private static Position parsePosition(String str, Position[][] board) {
        int column = str.charAt(0) - ROW_OFFSET;
        int row = COLUMN_OFFSET - str.charAt(1);
        if (row < 0 || row >= 8 || column < 0 || column >= 8) {
            throw new PositionParseException(str);
        }
        return board[row][column];
    }

    @Override
    public List<Position> movablePosition(String position) {
        Position p = parsePosition(position, board);
        if (p.peek().isEmpty()) {
            throw new ThereIsNoPieceException(position);
        } else {
            return p.peek().get().movablePosition(p, board);
        }
    }

    @Override
    public boolean move(String from, String to) {
        Position positionFrom = parsePosition(from, board);
        Position positionTo = parsePosition(to, board);
        Optional<Piece> piece = positionFrom.peek();
        if (piece.isEmpty()) {
            throw new ThereIsNoPieceException(from);
        }
        if (piece.get().side() != isWhiteTurn) {
            throw new NotYourTurnException();
        }
        if (!piece.get().canMove(positionFrom, positionTo, board)) {
            throw new PieceCanNotMoveException(from, to);
        }

        Optional<Piece> prePiece = positionTo.setPiece(piece);
        if (prePiece.isPresent() && prePiece.get().isKing()) {
            isEnd = true;
            isWhiteWin = !prePiece.get().side();
        }
        isWhiteTurn ^= true;
        return true;
    }

    private static final String WHITE = "WHITE";
    private static final String BLACK = "BLACK";
    @Override
    public String whoseTurn() {
        return isWhiteTurn ? WHITE : BLACK;
    }

    @Override
    public GameResult gameResult() {
        return GameResultImpl.build(isEnd, isWhiteWin);
    }

    private void setPiecesDefault() {
        Piece blackKing = new King(false);
        Piece whiteKing = new King(true);
        Piece blackQueen = new Queen(false);
        Piece whiteQueen = new Queen(true);
        Piece blackRook = new Rook(false);
        Piece whiteRook = new Rook(true);
        Piece blackBishop = new Bishop(false);
        Piece whiteBishop = new Bishop(true);
        Piece blackKnight = new Knight(false);
        Piece whiteKnight = new Knight(true);
        Piece blackPawn = new Pawn(false);
        Piece whitePawn = new Pawn(true);

        board[0][3].setPiece(Optional.of(blackKing));
        board[7][3].setPiece(Optional.of(whiteKing));

        board[0][4].setPiece(Optional.of(blackQueen));
        board[7][4].setPiece(Optional.of(whiteQueen));

        board[0][0].setPiece(Optional.of(blackRook));
        board[0][7].setPiece(Optional.of(blackRook));
        board[7][0].setPiece(Optional.of(whiteRook));
        board[7][7].setPiece(Optional.of(whiteRook));

        board[0][2].setPiece(Optional.of(blackBishop));
        board[0][5].setPiece(Optional.of(blackBishop));
        board[7][2].setPiece(Optional.of(whiteBishop));
        board[7][5].setPiece(Optional.of(whiteBishop));

        board[0][1].setPiece(Optional.of(blackKnight));
        board[0][6].setPiece(Optional.of(blackKnight));
        board[7][1].setPiece(Optional.of(whiteKnight));
        board[7][6].setPiece(Optional.of(whiteKnight));

        for (int i = 0; i < 8; i++) {
            board[1][i].setPiece(Optional.of(blackPawn));
            board[6][i].setPiece(Optional.of(whitePawn));
        }
    }
}
