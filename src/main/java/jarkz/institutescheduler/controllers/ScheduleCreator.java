package jarkz.institutescheduler.controllers;

import jarkz.institutescheduler.businesslogic.ScheduleManager;
import jarkz.institutescheduler.exceptions.InvalidSchedule;
import jarkz.institutescheduler.types.dto.NewSchedule;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/schedule-creator")
public class ScheduleCreator {

  private ScheduleManager scheduleManager;

  public ScheduleCreator(ScheduleManager scheduleManager) {
    this.scheduleManager = scheduleManager;
  }

  @GetMapping
  public String getPage(
      @RequestParam(required = false, name="lesson-date", defaultValue = "") String lessonDate,
      @RequestParam(required = false, defaultValue = "") String error,
      Model model) {
    if (lessonDate.isBlank()) {
      lessonDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
    model.addAttribute("lesson_date", lessonDate);
    model.addAttribute("error", error);

    scheduleManager.addRoomsAttr(model);
    scheduleManager.addSubjectsAttr(model);
    scheduleManager.addStudentGroupsAttr(model);

    return Views.ScheduleCreator.getName();
  }

  @PostMapping
  public String createSchedule(NewSchedule newSchedule, Principal principal) {
    try {
      scheduleManager.createSchedule(newSchedule, principal.getName());
    } catch (InvalidSchedule exception) {
      return "redirect:/" + Views.ScheduleCreator.getName() + "?error=" + exception.getMessage();
    }

    return "redirect:/" + Views.Calendar.getName();
  }
}
