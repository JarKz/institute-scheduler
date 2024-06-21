package jarkz.institutescheduler.exceptions;

public class InvalidSchedule extends Exception {

  public enum Reason {
    LessonDateBeforeCurrent(
        "The scheduled lesson date is past of current date. Please use future dates instead of"
            + " past."),
    StartIsAfterEnd(
        "The lesson start is after the end. Please set the start sooner than the end or set the end"
            + " later than the start."),
    NegativeBreakTime(
        "The break time is negative and that's impossible to reality. Please set zero or any"
            + " reasonable positive time in minutes."),
    InvalidGroupId(
        "The selected student group cannot be found. Please contact the administrator if you found"
            + " that it's error. Or, please, set another valid student group."),
    InvalidRoomId(
        "The selected room number cannot be found. Please contact the administrator if you found"
            + " that it's error. Or, please, set another valid room number."),
    InvalidSubjectId(
        "The selected subject name cannot be found. Please contact the administrator if you found"
            + " that it's error. Or, please, set another valid subject name."),
    InvalidDateFormat(
        "The used date is in invalid format. Please use the ISO format, which means YYYY-mm-dd"
            + " (e.g. 2024-08-22)."),
    InvalidTimeFormat(
        "The used time is in invalid format. Please use the ISO format, which means HH:mm:ss (e.g."
            + " 14:08:33)");

    private String readableName;

    private Reason(String readableName) {
      this.readableName = readableName;
    }

    public InvalidSchedule raiseException() {
      return new InvalidSchedule(this);
    }

    public String getReadableName() {
      return readableName;
    }
  }

  private InvalidSchedule(Reason reason) {
    super(reason.getReadableName());
  }
}
