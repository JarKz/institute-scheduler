package jarkz.institutescheduler.types;

import com.google.gson.annotations.SerializedName;
import jarkz.institutescheduler.entities.Subject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ShortSchedule {

  private long id;

  @SerializedName("lesson_date")
  private String lessonDate;

  @SerializedName("lesson_start")
  private String lessonStart;

  @SerializedName("lesson_end")
  private String lessonEnd;

  private String subject;

  public ShortSchedule(
      long id, LocalDate lessonDate, LocalTime lessonStart, LocalTime lessonEnd, Subject subject) {
    this.id = id;
    this.lessonDate = lessonDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    this.lessonStart = lessonStart.format(DateTimeFormatter.ISO_LOCAL_TIME);
    this.lessonEnd = lessonEnd.format(DateTimeFormatter.ISO_LOCAL_TIME);
    this.subject = subject.name;
  }
}
