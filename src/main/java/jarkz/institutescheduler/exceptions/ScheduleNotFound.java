package jarkz.institutescheduler.exceptions;

public class ScheduleNotFound extends Exception {

  public ScheduleNotFound() {
    super(
        "The requested schedule doesn't exists. If you found that it's wrong, please contact to"
            + " administrator.");
  }
}
