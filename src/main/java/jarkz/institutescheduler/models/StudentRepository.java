package jarkz.institutescheduler.models;

import jarkz.institutescheduler.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
  Student findByUsername(String username);
}
