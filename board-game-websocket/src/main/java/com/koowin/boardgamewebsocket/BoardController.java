package com.koowin.boardgamewebsocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @GetMapping("/")
    public String getBoard() {
        return "board.html";
    }
}
