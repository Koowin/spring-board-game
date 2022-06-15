package com.koowin.boardgame.game.chess;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.koowin.boardgame.game.GameResult;
import lombok.RequiredArgsConstructor;


public class GameResultImpl implements GameResult {
    @JsonProperty
    private final boolean isGameEnd;
    @JsonProperty
    private final String winner;

    private GameResultImpl(boolean isGameEnd, String winner) {
        this.isGameEnd = isGameEnd;
        this.winner = winner;
    }

    public static GameResultImpl build(boolean isGameEnd, boolean isWhiteWin){
        if (isGameEnd) {
            if (isWhiteWin) {
                return new GameResultImpl(true, "WHITE");
            } else {
                return new GameResultImpl(true, "BLACK");
            }
        } else {
            return new GameResultImpl(false, null);
        }
    }

    @JsonIgnore
    @Override
    public boolean isGameEnd() {
        return isGameEnd;
    }
}
