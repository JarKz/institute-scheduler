package jarkz.institutescheduler.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.ToString;

@Entity
@ToString
public class Student extends User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  public String firstName;
  public String lastName;
  public String patronymic;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "faculty_id")
  public Faculty faculty;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "speciality_id")
  public Speciality speciality;

  public long phoneNumber;
  public short course;

  public static class Builder {
    private Student data = new Student();

    public Builder setFirstName(String firstName) {
      data.firstName = firstName;
      return this;
    }

    public Builder setLastName(String lastName) {
      data.lastName = lastName;
      return this;
    }

    public Builder setPatronymic(String patronymic) {
      data.patronymic = patronymic;
      return this;
    }

    public Builder setFaculty(Faculty faculty) {
      data.faculty = faculty;
      return this;
    }

    public Builder setSpeciality(Speciality speciality) {
      data.speciality = speciality;
      return this;
    }

    public Builder setPhoneNumber(long phoneNumber) {
      data.phoneNumber = phoneNumber;
      return this;
    }

    public Builder setCourse(short course) {
      data.course = course;
      return this;
    }

    public Student build() {
      return data;
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("STUDENT"));
  }
}
