package jarkz.institutescheduler.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class Subject {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  public String name;

  public Subject(String name) {
    this.name = name;
  }
}
