package jarkz.institutescheduler.businesslogic;

import jarkz.institutescheduler.models.RoomRepository;
import jarkz.institutescheduler.models.ScheduleRepository;
import jarkz.institutescheduler.models.StudentGroupRepository;
import jarkz.institutescheduler.models.StudentRepository;
import jarkz.institutescheduler.models.SubjectRepository;
import jarkz.institutescheduler.models.TeacherRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class BusinessLogicService {

  @Bean
  public Events getEvents(
      StudentRepository studentRepository,
      TeacherRepository teacherRepository,
      ScheduleRepository scheduleRepository) {
    return new Events(studentRepository, teacherRepository, scheduleRepository);
  }

  @Bean
  public ScheduleManager getScheduleManager(
      TeacherRepository teacherRepository,
      StudentGroupRepository studentGroupRepository,
      RoomRepository roomRepository,
      SubjectRepository subjectRepository,
      ScheduleRepository scheduleRepository) {
    return new ScheduleManager(
        teacherRepository,
        studentGroupRepository,
        scheduleRepository,
        roomRepository,
        subjectRepository);
  }
}
