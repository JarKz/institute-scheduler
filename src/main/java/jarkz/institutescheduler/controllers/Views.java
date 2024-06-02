package jarkz.institutescheduler.controllers;

public enum Views {
  Home("home"),
  Login("login");

  private String name;

  private Views(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
