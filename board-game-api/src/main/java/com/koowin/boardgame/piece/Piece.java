package com.koowin.boardgame.piece;

import com.koowin.boardgame.position.Position;

import java.util.List;

public interface Piece {
    /**
     * 기물의 현재 위치와 판 정보를 입력으로 받아 이동 가능한 위치들을 계산한다
     *
     * @param from 기물의 현재 위치
     * @param board (판 정보)모든 위치의 정보
     * @return 이동 가능한 위치 리스트
     */
    List<Position> movablePosition(Position from, Position[][] board);

    /**
     * 기물이 현재 위치에서 다음 위치로 이동 가능한지 확인한다.
     *
     * @param from 기물의 현재 위치
     * @param to 기물의 다음 위치
     * @param board (판 정보)모든 위치의 정보
     * @return 이동 가능 여부
     */
    boolean canMove(Position from, Position to, Position[][] board);

    /**
     * 기물의 진영 정보를 확인한다.
     *
     * @return 기물의 진영
     */
    boolean side();

    /**
     * 기물이 왕인지 확인한다. (게임 종료 여부를 결정짓기 위해 사용)
     *
     * @return 기물이 왕인지 여부
     */
    boolean isKing();
}
