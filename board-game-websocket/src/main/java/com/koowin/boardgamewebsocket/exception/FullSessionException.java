package com.koowin.boardgamewebsocket.exception;

public class FullSessionException extends RuntimeException{
    private static final String message = "이미 두 명이 접속했습니다";
    public FullSessionException() {
        super(message);
    }
}
