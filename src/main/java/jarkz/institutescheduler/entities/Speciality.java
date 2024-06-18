package jarkz.institutescheduler.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class Speciality {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  public String name;

  public Speciality() {}

  public Speciality(String name) {
    this.name = name;
  }
}
