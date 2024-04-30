package jarkz.institutescheduler.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

  @GetMapping("/home")
  public String homePage() {
    return Views.Home.getName();
  }
}
