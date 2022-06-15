package com.koowin.boardgame.game;

import com.koowin.boardgame.game.exceptions.*;
import com.koowin.boardgame.piece.Piece;
import com.koowin.boardgame.position.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class GameController {
    private final Map<String, Game> gameMap;
    private Game game;

    @PostMapping("/game")
    void newGame(@RequestBody String gameType) {
        game = gameMap.get(gameType);
        if (game == null) {
            throw new GameNotFoundException(gameType);
        }
        game.startNewGame();
    }

    @PostMapping("/game/peek")
    @ResponseBody
    Optional<Piece> peek(@RequestBody String position) {
        if (game == null) {
            throw new GameNotStartedException();
        } else if (game.gameResult().isGameEnd()) {
            throw new GameEndException();
        }
        return game.peek(position);
    }

    @PostMapping("game/movable")
    @ResponseBody
    List<String> movable(@RequestBody String position) {
        if (game == null) {
            throw new GameNotStartedException();
        } else if (game.gameResult().isGameEnd()) {
            throw new GameEndException();
        }
        List<Position> positions = game.movablePosition(position);
        List<String> ret = new ArrayList<>();
        for (Position p : positions) {
            ret.add(p.toString());
        }
        return ret;
    }

    @PostMapping("game/move")
    @ResponseBody
    boolean move(@RequestBody String from, String to) {
        if (game == null) {
            throw new GameNotStartedException();
        } else if (game.gameResult().isGameEnd()) {
            throw new GameEndException();
        }
        game.move(from, to);
        return true;
    }

    @GetMapping("game/turn")
    @ResponseBody
    String whoseTurn() {
        if (game == null) {
            throw new GameNotStartedException();
        } else if (game.gameResult().isGameEnd()) {
            throw new GameEndException();
        }
        return game.whoseTurn();
    }

    @GetMapping("game/result")
    @ResponseBody
    GameResult result() {
        GameResult result = game.gameResult();
        if (!result().isGameEnd()) {
            throw new GameNotEndException();
        }
        return result;
    }
}
