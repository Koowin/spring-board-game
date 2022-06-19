package com.koowin.chesswithreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping
    public String getIndexForm() {
        return "index";
    }
}
