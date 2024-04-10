package jarkz.institutescheduler.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Schedule {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  public LocalDate date;
  public LocalTime start;
  public LocalTime end;
  public int breakInMinutes;
  public Teacher teacher;
  public List<Student> students;
  public Room room;
  public Subject subject;

  public static class Builder {
    private Schedule data;

    public Builder setDate(LocalDate date) {
      data.date = date;
      return this;
    }

    public Builder setStart(LocalTime start) {
      data.start = start;
      return this;
    }

    public Builder setEnd(LocalTime end) {
      data.end = end;
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

  private Schedule() {}
}
