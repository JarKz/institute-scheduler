package jarkz.institutescheduler.models;

import jarkz.institutescheduler.entities.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {}
