package com.koowin.multiplayer.controller;

import com.koowin.multiplayer.dto.request.CreateGameRoomForm;
import com.koowin.multiplayer.repository.GameRoomRepository;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class GameController {

  private final GameRoomRepository gameRoomRepository;

  @GetMapping("/")
  public String corridor(Model model, HttpSession session) {
    log.info("Session, uid: " + session.getAttribute("uid"));
    return "corridor";
  }

  @GetMapping("/games/{id}")
  public String gameRoom(@PathVariable String id, Model model) {
    return "board";
  }

  @GetMapping("/games")
  public String createGameRoomForm(Model model) {
    CreateGameRoomForm form = new CreateGameRoomForm();
    model.addAttribute("form", form);
    return "createGameRoom";
  }

  @PostMapping("/games")
  public String createGameRoom(CreateGameRoomForm form) {

    return "redirect:/";
  }
}
