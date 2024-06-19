package jarkz.institutescheduler.controllers;

import com.google.gson.Gson;
import jarkz.institutescheduler.businesslogic.Events;
import jarkz.institutescheduler.entities.Schedule;
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
    final var gson = new Gson();
    final var upcomingSchedules =
        events.getUpcomingShedulesFor(username).stream()
            .map(Schedule::toShortSchedule)
            .map(shortSchedule -> gson.toJson(shortSchedule))
            .toList();

    model.addAttribute("upcoming_schedules", upcomingSchedules);

    return Views.Calendar.getName();
  }
}
