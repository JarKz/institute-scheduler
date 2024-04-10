package jarkz.institutescheduler.models;

import jarkz.institutescheduler.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {}
