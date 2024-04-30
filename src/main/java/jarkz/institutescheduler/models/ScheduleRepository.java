package jarkz.institutescheduler.models;

import org.springframework.data.jpa.repository.JpaRepository;

import jarkz.institutescheduler.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {}
