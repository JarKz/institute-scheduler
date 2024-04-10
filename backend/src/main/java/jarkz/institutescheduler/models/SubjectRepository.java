package jarkz.institutescheduler.models;

import jarkz.institutescheduler.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {}
