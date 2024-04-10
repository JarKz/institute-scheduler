package jarkz.institutescheduler.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  public int floor;
  public int number;

  public Room(int floor, int number) {
    this.floor = floor;
    this.number = number;
  }
}
