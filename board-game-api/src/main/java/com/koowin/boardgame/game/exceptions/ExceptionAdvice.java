package com.koowin.boardgame.game.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(GameEndException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String gameEndHandler(GameEndException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(GameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String gameNotFoundHandler(GameNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(GameNotStartedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String gameNotStartedHandler(GameNotStartedException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PositionParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String positionExceptionHandler(PositionParseException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ThereIsNoPieceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String noPieceExceptionHandler(ThereIsNoPieceException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(GameNotEndException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String gameNotEndHandler(GameNotEndException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PieceCanNotMoveException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String pieceCanNotMoveHandler(PieceCanNotMoveException ex) {
        return ex.getMessage();
    }
}
