package miu.edu.attendance.service;

import miu.edu.attendance.domain.*;
import miu.edu.attendance.dto.BarcodeRecordDTO;
import miu.edu.attendance.dto.ClassSessionDTO;
import miu.edu.attendance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BarcodeRecordServiceImpl implements BarcodeRecordService {
    @Autowired
    BarcodeRecordRepository barcodeRecordRepository;

    @Autowired
    LocationRepository locationalRepository;

    @Autowired
    ClassSessionRepository classSessionRepository;

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ClassSessionService classSessionService;

    @Autowired
    BarcodeRecordService barcodeRecordService;

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

// The barcode reader creates a record of each scan by saving the bar-code ID, date, timeslot
//(morning or afternoon) and location.

    public BarcodeRecord createBarcodeRecord(BarcodeRecordDTO barcodeRecordDTO) {
        Person person = personRepository.findByBarcodeId(barcodeRecordDTO.getBarcode()).orElseThrow(() -> new EntityNotFoundException(String.format("Invalid barcode=%s. Student not found", barcodeRecordDTO.getBarcode())));
        Student student = person.asStudent().orElseThrow(() -> new IllegalStateException(String.format("The person with ID=%d is not a Student.", person.getId())));

        BarcodeRecord barcodeRecord = new BarcodeRecord();
        Location location = locationalRepository.findById(barcodeRecordDTO.getLocationId()).orElseThrow(() -> new EntityNotFoundException(String.format("Location ID=%d does not exist", barcodeRecordDTO.getLocationId())));
        List<ClassSession> classSessions = classSessionService.findByLocationAndDate(location, LocalDate.now());
        LocalTime timeNow = LocalTime.now();
        ClassSession classSession = classSessions.stream()
                .filter(session -> session.getTimeSlot().getBeginTime().isBefore(timeNow)
                        && session.getTimeSlot().getEndTime().isAfter(timeNow))
                .findAny().orElseThrow(() -> new EntityNotFoundException(String.format("There is no class session at this time")));

        barcodeRecord.setClassSession(classSession);
        barcodeRecord.setLocation(location);
        barcodeRecord.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        Optional<BarcodeRecord> barcodeRecordStudentOptional = barcodeRecordService.findByClassSessionAndStudent(classSession.getId(), student.getId());
        if(barcodeRecordStudentOptional.isPresent()) {
            throw new IllegalStateException("your attendance is already recorded for this session");
        }
        barcodeRecordRepository.save(barcodeRecord);

        student.addBarcodeRecord(barcodeRecord);
        personRepository.save(person);
        return barcodeRecord;
    }

    @Override
    public List<BarcodeRecord> getBarcodeRecordByStudentIdAndCourseOfferId(Integer studentId, Integer sessionId) {
        return barcodeRecordRepository.getBarcodeRecordByStudentIdAndCourseOfferId(studentId, sessionId);
    }

    public List<BarcodeRecord> getAll() {
        return barcodeRecordRepository.findAll();
    }

    public BarcodeRecord createBarcodeRecordToStudent(ClassSessionDTO classSessionDTO){
        BarcodeRecord barcodeRecord = new BarcodeRecord();
        barcodeRecord.setClassSession(classSessionDTO.getClassSession());
        barcodeRecord.setLocation(classSessionDTO.getClassSession().getLocation());
        LocalDateTime timeStamp = LocalDateTime.of(classSessionDTO.getClassSession().getDate(), classSessionDTO.getClassSession().getTimeSlot().getBeginTime() );
        barcodeRecord.setTimestamp(timeStamp.toEpochSecond(ZoneOffset.UTC));
        Student student = studentService.getStudentById(classSessionDTO.getStudent().getId());

        Optional<BarcodeRecord> barcodeRecordOptional = barcodeRecordService.findByClassSessionAndStudent(classSessionDTO.getClassSession().getId(), student.getId());
        if(barcodeRecordOptional.isPresent()) {
            throw new IllegalStateException("Student is already register in this class session");
        }

        barcodeRecordRepository.save(barcodeRecord);
        student.addBarcodeRecord(barcodeRecord);
        studentRepository.save(student);
        return barcodeRecord;
    }
    public List<ClassSession> getBarcodeRecordAttendance(Integer studentId){
       return barcodeRecordRepository.getBarcodeRecordAttendance(studentId);
    }

    @Override
    public Optional<BarcodeRecord> findByClassSessionAndStudent(Integer classSessionId, Integer studentId) {
        ClassSession classSession = new ClassSession();
        classSession.setId(classSessionId);
        Student student = new Student();
        student.setId(studentId);
        return barcodeRecordRepository.findByClassSessionAndStudent(classSession, student);
    }

    @Override
    public Optional<BarcodeRecord> findByBarcodeRecordId(Long barcodeRecordId){
        return barcodeRecordRepository.findByBarcodeId(barcodeRecordId);
    }

    @Override
    public void deleteBarcodeRecord(Integer barcodeRecordId){
        BarcodeRecord barcodeRecord1 = new BarcodeRecord();
        barcodeRecord1.setId(barcodeRecordId);
        Optional<BarcodeRecord> barcodeRecordOptional =  barcodeRecordService.findByBarcodeRecordId(barcodeRecordId.longValue());
        if(!barcodeRecordOptional.isPresent()) {
            throw new IllegalStateException("Action is not supported, register si not in data base");
        }
        barcodeRecordRepository.delete(barcodeRecord1);

    }
}
