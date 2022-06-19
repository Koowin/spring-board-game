package com.koowin.chesswithreview.controller;

import com.koowin.chesswithreview.dto.request.MemberRegisterDto;
import com.koowin.chesswithreview.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/register")
    public String getRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(MemberRegisterDto memberRegisterDto) {
        memberService.join(memberRegisterDto);
        return "login";
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "login";
    }
}
