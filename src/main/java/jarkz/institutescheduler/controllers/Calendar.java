package jarkz.institutescheduler.controllers;

import jarkz.institutescheduler.businesslogic.Events;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Calendar {

  private Events events;

  public Calendar(Events events) {
    this.events = events;
  }

  @GetMapping("/calendar")
  public String getCalendarPage(Principal principal, Model model) {
    final var username = principal.getName();
    final var upcomingSchedules = events.getUpcomingShedulesFor(username);


    return Views.Calendar.getName();
  }
}
