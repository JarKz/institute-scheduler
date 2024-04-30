package jarkz.institutescheduler.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class Login {

  @GetMapping
  public String getLoginPage(
      @RequestParam(name = "error", required = false) String errorMessage, Model model) {
    if (errorMessage != null) {
      model.addAttribute("error_message", errorMessage);
    }
    return Views.Login.getName();
  }
}
