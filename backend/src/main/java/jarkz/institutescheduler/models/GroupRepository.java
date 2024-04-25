package jarkz.institutescheduler.models;

import jarkz.institutescheduler.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {}
