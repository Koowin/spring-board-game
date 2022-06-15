package com.koowin.boardgame.position;

import com.koowin.boardgame.piece.Piece;

import java.util.Optional;

public interface Position {
    /**
     * 현재 칸의 기물을 확인한다
     *
     * @return 현재 칸의 기물
     */
    Optional<Piece> peek();

    /**
     * 새로운 기물을 현재 칸에 놓는다
     *
     * @param nextPiece 현재 칸에 놓을 새로운 기물
     * @return 기존 기물
     */
    Optional<Piece> setPiece(Optional<Piece> nextPiece);

    /**
     * 현재 칸의 행을 구한다
     *
     * @return 현재 칸의 행
     */
    int getRow();

    /**
     * 현재 칸의 열을 구한다
     *
     * @return 현재 칸의 열
     */
    int getColumn();
}
