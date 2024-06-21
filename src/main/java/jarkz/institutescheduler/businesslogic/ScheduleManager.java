package jarkz.institutescheduler.businesslogic;

import com.google.gson.GsonBuilder;
import jarkz.institutescheduler.entities.Schedule;
import jarkz.institutescheduler.exceptions.InvalidSchedule;
import jarkz.institutescheduler.models.RoomRepository;
import jarkz.institutescheduler.models.ScheduleRepository;
import jarkz.institutescheduler.models.StudentGroupRepository;
import jarkz.institutescheduler.models.SubjectRepository;
import jarkz.institutescheduler.models.TeacherRepository;
import jarkz.institutescheduler.types.dto.NewSchedule;

import java.util.ArrayList;

import org.springframework.ui.Model;

public class ScheduleManager {

  private TeacherRepository teacherRepository;
  private StudentGroupRepository studentGroupRepository;

  private ScheduleRepository scheduleRepository;
  private RoomRepository roomRepository;
  private SubjectRepository subjectRepository;

  public ScheduleManager(
      TeacherRepository teacherRepository,
      StudentGroupRepository studentGroupRepository,
      ScheduleRepository scheduleRepository,
      RoomRepository roomRepository,
      SubjectRepository subjectRepository) {
    this.teacherRepository = teacherRepository;
    this.studentGroupRepository = studentGroupRepository;
    this.scheduleRepository = scheduleRepository;
    this.roomRepository = roomRepository;
    this.subjectRepository = subjectRepository;
  }

  public void addStudentGroupsAttr(Model model) {
    model.addAttribute("student_groups", studentGroupRepository.findAll());
  }

  public void addRoomsAttr(Model model) {
    model.addAttribute("rooms", roomRepository.findAll());
  }

  public void addSubjectsAttr(Model model) {
    model.addAttribute("subjects", subjectRepository.findAll());
  }

  public void createSchedule(NewSchedule newSchedule, String teacherUsername)
      throws InvalidSchedule {
    newSchedule.validate();

    // TODO: check is the room busy or not
    var room =
        roomRepository
            .findById(newSchedule.getRoomId())
            .orElseThrow(InvalidSchedule.Reason.InvalidRoomId::raiseException);

    var subject =
        subjectRepository
            .findById(newSchedule.getSubjectId())
            .orElseThrow(InvalidSchedule.Reason.InvalidSubjectId::raiseException);

    // TODO: check is the student group is busy at this time or not
    var studentGroup =
        studentGroupRepository
            .findById(newSchedule.getGroupId())
            .orElseThrow(InvalidSchedule.Reason.InvalidGroupId::raiseException);

    var teacher = teacherRepository.findByUsername(teacherUsername);

    var schedule =
        new Schedule.Builder()
            .setLessonDate(newSchedule.getLessonDate())
            .setLessonStart(newSchedule.getLessonStart())
            .setLessonEnd(newSchedule.getLessonEnd())
            .setBreak(newSchedule.getBreakInMinutes())
            .setRoom(room)
            .setSubject(subject)
            .setStudents(new ArrayList<>(studentGroup.students))
            .setTeacher(teacher)
            .build();

    scheduleRepository.saveAndFlush(schedule);
  }
}
