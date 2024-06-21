package jarkz.institutescheduler.models;

import jarkz.institutescheduler.entities.Student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
  Optional<Student> findByUsername(String username);
}
