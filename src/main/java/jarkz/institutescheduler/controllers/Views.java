package jarkz.institutescheduler.controllers;

public enum Views {
  Home("home"),
  Login("login"),
  Calendar("calendar"),
  Schedule("schedule-info"),
  UserInfo("user-info"),
  UpcomingEvents("upcoming-events"),
  ScheduleCreator("schedule-creator");

  private String name;

  private Views(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
