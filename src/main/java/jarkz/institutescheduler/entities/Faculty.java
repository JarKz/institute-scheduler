package jarkz.institutescheduler.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class Faculty {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  public String name;

  public Faculty() {}

  public Faculty(String name) {
    this.name = name;
  }
}
