package jarkz.institutescheduler.models;

import jarkz.institutescheduler.entities.Teacher;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
  Optional<Teacher> findByUsername(String username);
}
