package jarkz.institutescheduler.models;

import jarkz.institutescheduler.entities.Schedule;
import jarkz.institutescheduler.entities.Student;
import jarkz.institutescheduler.entities.Teacher;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  @EntityGraph(attributePaths = {"subject"})
  List<Schedule> findByStudentsContainingAndLessonDateGreaterThanEqual(
      Student student, LocalDate lessonDate);

  @EntityGraph(attributePaths = {"subject"})
  List<Schedule> findByTeacherAndLessonDateGreaterThanEqual(Teacher teacher, LocalDate lessonDate);
}
