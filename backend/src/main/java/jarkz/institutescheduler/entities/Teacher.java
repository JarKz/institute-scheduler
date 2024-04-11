package jarkz.institutescheduler.entities;

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
public class Teacher {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  public String firstName;
  public String lastName;
  public String patronymic;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "faculty_id")
  public Faculty faculty;

  public long phoneNumber;
  public String department;

  public static class Builder {
    private Teacher data = new Teacher();

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

    public Builder setPhoneNumber(long phoneNumber) {
      data.phoneNumber = phoneNumber;
      return this;
    }

    public Builder setDepartment(String department) {
      data.department = department;
      return this;
    }

    public Teacher build() {
      return data;
    }
  }

  private Teacher() {}
}
