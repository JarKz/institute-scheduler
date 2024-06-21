package jarkz.institutescheduler.models;

import jarkz.institutescheduler.entities.Administrator;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
  Optional<Administrator> findByUsername(String username);
}

