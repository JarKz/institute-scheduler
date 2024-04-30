package jarkz.institutescheduler.models;

import jarkz.institutescheduler.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {}
