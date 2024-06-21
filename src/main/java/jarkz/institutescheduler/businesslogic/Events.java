package jarkz.institutescheduler.businesslogic;

import jarkz.institutescheduler.entities.Schedule;
import jarkz.institutescheduler.models.ScheduleRepository;
import jarkz.institutescheduler.models.StudentRepository;
import jarkz.institutescheduler.models.TeacherRepository;

import java.time.LocalDate;
import java.util.List;

public class Events {

  private StudentRepository studentRepository;
  private TeacherRepository teacherRepository;
  private ScheduleRepository scheduleRepository;

  public Events(
      StudentRepository studentRepository,
      TeacherRepository teacherRepository,
      ScheduleRepository scheduleRepository) {
    this.studentRepository = studentRepository;
    this.teacherRepository = teacherRepository;
    this.scheduleRepository = scheduleRepository;
  }

  public List<Schedule> getUpcomingShedulesFor(final String username) {
    var student = studentRepository.findByUsername(username);
    if (student != null) {
      return scheduleRepository.findByStudentsContainingAndLessonDateGreaterThanEqual(student, LocalDate.now());
    }

    var teacher = teacherRepository.findByUsername(username);
    if (teacher != null) {
      return scheduleRepository.findByTeacherAndLessonDateGreaterThanEqual(teacher, LocalDate.now());
    }

    return List.of();
  }

  public boolean isTeacher(final String username) {
    return teacherRepository.findByUsername(username) != null;
  }
}
