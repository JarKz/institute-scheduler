package jarkz.institutescheduler.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jarkz.institutescheduler.entities.Schedule;
import jarkz.institutescheduler.entities.Student;
import jarkz.institutescheduler.entities.Teacher;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  List<Schedule> findByStudentsContainingAndLessonDateGreaterThanEqual(Student student, LocalDate lessonDate);
  List<Schedule> findByTeacherAndLessonDateGreaterThanEqual(Teacher teacher, LocalDate lessonDate);
}
