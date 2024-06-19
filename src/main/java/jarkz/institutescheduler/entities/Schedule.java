package jarkz.institutescheduler.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jarkz.institutescheduler.types.ShortSchedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.ToString;

@Entity
@ToString
public class Schedule {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  public LocalDate lessonDate;
  public LocalTime lessonStart;
  public LocalTime lessonEnd;
  public int breakInMinutes;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "teacher_id")
  public Teacher teacher;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  public List<Student> students;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "room_id")
  public Room room;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "subject_id")
  public Subject subject;

  public static class Builder {
    private Schedule data = new Schedule();

    public Builder setLessonDate(LocalDate lessonDate) {
      data.lessonDate = lessonDate;
      return this;
    }

    public Builder setLessonStart(LocalTime lessonStart) {
      data.lessonStart = lessonStart;
      return this;
    }

    public Builder setLessonEnd(LocalTime lessonEnd) {
      data.lessonEnd = lessonEnd;
      return this;
    }

    public Builder setBreak(int breakInMinutes) {
      data.breakInMinutes = breakInMinutes;
      return this;
    }

    public Builder setTeacher(Teacher teacher) {
      data.teacher = teacher;
      return this;
    }

    public Builder setStudents(List<Student> students) {
      data.students = students;
      return this;
    }

    public Builder setRoom(Room room) {
      data.room = room;
      return this;
    }

    public Builder setSubject(Subject subject) {
      data.subject = subject;
      return this;
    }

    public Schedule build() {
      return data;
    }
  }

  public ShortSchedule toShortSchedule() {
    return new ShortSchedule(lessonDate, lessonStart, lessonEnd, subject);
  }

  private Schedule() {}
}
