package jarkz.institutescheduler.types.dto;

import jarkz.institutescheduler.exceptions.InvalidSchedule;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@ToString
@AllArgsConstructor
public class NewSchedule {

  private String lessonDate;
  private String lessonStart;
  private String lessonEnd;

  private int breakInMinutes;

  private long groupId;
  private long roomId;
  private long subjectId;

  public void validate() throws InvalidSchedule {
    var datetime = LocalDateTime.now();
    try {
      var scheduledDate = LocalDate.parse(lessonDate);
      if (datetime.toLocalDate().compareTo(scheduledDate) > 0) {
        throw InvalidSchedule.Reason.LessonDateBeforeCurrent.raiseException();
      }
    } catch (DateTimeParseException exception) {
      throw InvalidSchedule.Reason.InvalidDateFormat.raiseException();
    }

    try {
      var start = LocalTime.parse(lessonStart);
      var end = LocalTime.parse(lessonEnd);
      if (start.compareTo(end) >= 0) {
        throw InvalidSchedule.Reason.StartIsAfterEnd.raiseException();
      }
    } catch (DateTimeParseException exception) {
      throw InvalidSchedule.Reason.InvalidTimeFormat.raiseException();
    }

    if (breakInMinutes < 0) {
      throw InvalidSchedule.Reason.NegativeBreakTime.raiseException();
    }
  }

  public LocalDate getLessonDate() {
    return LocalDate.parse(lessonDate);
  }

  public LocalTime getLessonStart() {
    return LocalTime.parse(lessonStart);
  }

  public LocalTime getLessonEnd() {
    return LocalTime.parse(lessonEnd);
  }

  public int getBreakInMinutes() {
    return breakInMinutes;
  }

  public long getGroupId() {
    return groupId;
  }

  public long getRoomId() {
    return roomId;
  }

  public long getSubjectId() {
    return subjectId;
  }
}
