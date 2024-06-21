package jarkz.institutescheduler.types;

import jarkz.institutescheduler.entities.Room;
import jarkz.institutescheduler.entities.Schedule;
import jarkz.institutescheduler.entities.Student;
import jarkz.institutescheduler.entities.Subject;
import jarkz.institutescheduler.entities.Teacher;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.Data;

@Data
public class ScheduleInfoModel {

  private String lessonDate;
  private String lessonStart;
  private String lessonEnd;
  private int breakInMinutes;

  private Teacher teacher;
  private List<Student> students;

  private Room room;
  private Subject subject;

  public ScheduleInfoModel(
      String lessonDate,
      String lessonStart,
      String lessonEnd,
      int breakInMinutes,
      Teacher teacher,
      List<Student> students,
      Room room,
      Subject subject) {
    this.lessonDate = lessonDate;
    this.lessonStart = lessonStart;
    this.lessonEnd = lessonEnd;
    this.breakInMinutes = breakInMinutes;
    this.teacher = teacher;
    this.students = students;
    this.room = room;
    this.subject = subject;
  }

  public static ScheduleInfoModel fromSchedule(Schedule schedule) {
    return new ScheduleInfoModel(
        schedule.lessonDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
        schedule.lessonStart.format(DateTimeFormatter.ISO_LOCAL_TIME),
        schedule.lessonEnd.format(DateTimeFormatter.ISO_LOCAL_TIME),
        schedule.breakInMinutes,
        schedule.teacher,
        schedule.students,
        schedule.room,
        schedule.subject);
  }
}
