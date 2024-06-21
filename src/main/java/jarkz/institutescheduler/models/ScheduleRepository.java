package jarkz.institutescheduler.models;

import jarkz.institutescheduler.entities.Schedule;
import jarkz.institutescheduler.entities.Student;
import jarkz.institutescheduler.entities.Teacher;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  @EntityGraph(attributePaths = {"subject"})
  List<Schedule> findByStudentsContainingAndLessonDateGreaterThanEqual(
      Student student, LocalDate lessonDate);

  @EntityGraph(attributePaths = {"subject"})
  List<Schedule> findByTeacherAndLessonDateGreaterThanEqual(Teacher teacher, LocalDate lessonDate);

  @Query("""
    SELECT s FROM Schedule s
    JOIN FETCH s.teacher teacher
    JOIN FETCH s.students students
    JOIN FETCH s.room room
    JOIN FETCH s.subject subject
    WHERE s.id = :id
  """)
  Optional<Schedule> findByIdFully(@Param("id") long id);
}
