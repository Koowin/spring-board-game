package com.koowin.multiplayer.controller;

import com.koowin.multiplayer.dto.request.MemberRegisterDto;
import com.koowin.multiplayer.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/login")
  public String getLoginForm() {
    return "login";
  }

  @GetMapping("/register")
  public String registerForm(Model model) {
    model.addAttribute("registerForm", new MemberRegisterDto());
    return "register";
  }

  @PostMapping("/register")
  public String register(MemberRegisterDto memberRegisterDto) {
    memberService.join(memberRegisterDto);
    return "login";
  }

  @PostMapping("/logout")
  public void logout() {

  }
}
