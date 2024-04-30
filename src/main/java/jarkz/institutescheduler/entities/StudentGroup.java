package jarkz.institutescheduler.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.ToString;

@Entity
@ToString
public class StudentGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  public String name;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  public List<Student> students;
}
