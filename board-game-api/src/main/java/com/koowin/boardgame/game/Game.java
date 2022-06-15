package com.koowin.boardgame.game;

import com.koowin.boardgame.piece.Piece;
import com.koowin.boardgame.position.Position;

import java.util.List;
import java.util.Optional;

public interface Game {
    /**
     * 게임을 시작한다
     */
    void startNewGame();

    /**
     * 위치에 있는 기물을 확인한다
     *
     * @param position 확인하고자 하는 위치
     * @return 해당 위치의 기물
     */
    Optional<Piece> peek(String position);

    /**
     * 현재 위치에서 기물이 움직일 수 있는 다음 위치들을 확인한다.
     *
     * @param position 확인하고자 하는 위치
     * @return 움직일 수 있는 위치들의 목록
     */
    List<Position> movablePosition(String position);

    /**
     * 현재 위치에서 다음 위치로 기물을 이동한다.
     *
     * @param from 현재 위치
     * @param to 다음 위치
     * @return 성공 여부
     */
    boolean move(String from, String to);

    /**
     * 현재 차례를 확인한다.
     * @return
     */
    String whoseTurn();

    /**
     * 게임 종료 결과를 확인한다.
     * @return 종료 결과
     */
    GameResult gameResult();
}
