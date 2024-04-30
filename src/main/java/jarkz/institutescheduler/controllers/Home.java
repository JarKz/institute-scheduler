package jarkz.institutescheduler.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Home {

  @GetMapping("/home")
  public String homePage() {
    return Views.Home.getName();
  }
}
