package jarkz.institutescheduler.controllers;

import jarkz.institutescheduler.businesslogic.ScheduleManager;
import jarkz.institutescheduler.exceptions.ScheduleNotFound;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/schedule-info")
public class ScheduleInfo {

  private ScheduleManager scheduleManager;

  public ScheduleInfo(ScheduleManager scheduleManager) {
    this.scheduleManager = scheduleManager;
  }

  @GetMapping
  public String getScheduleInfo(@RequestParam(name = "id", required = true) long id, Model model) {
    try {
      var schedule = scheduleManager.getScheduleById(id);
      model.addAttribute("schedule", schedule);
    } catch (ScheduleNotFound exception) {
      // TODO: add warning with information about exception
      return "redirect:/" + Views.Calendar.getName();
    }

    return Views.Schedule.getName();
  }
}
