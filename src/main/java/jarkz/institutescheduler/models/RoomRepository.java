package jarkz.institutescheduler.models;

import jarkz.institutescheduler.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {}
