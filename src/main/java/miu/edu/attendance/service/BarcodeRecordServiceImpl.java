package miu.edu.attendance.service;

import miu.edu.attendance.domain.*;
import miu.edu.attendance.dto.BarcodeRecordDTO;
import miu.edu.attendance.repository.BarcodeRecordRepository;
import miu.edu.attendance.repository.ClassSessionRepository;
import miu.edu.attendance.repository.LocationRepository;
import miu.edu.attendance.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

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

// The barcode reader creates a record of each scan by saving the bar-code ID, date, timeslot
//(morning or afternoon) and location.

    //@Override
    public BarcodeRecord createBarcodeRecord(BarcodeRecordDTO barcodeRecordDTO) {
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

        barcodeRecordRepository.save(barcodeRecord);


        Person person = personRepository.findByBarcodeId(barcodeRecordDTO.getBarcode()).orElseThrow(() -> new EntityNotFoundException(String.format("Invalid barcode=%d. Student not found", barcodeRecordDTO.getBarcode())));
        ;
        Student student = person.asStudent();
        if (!student.isActive()) {
            throw new IllegalStateException(String.format("The student with ID=%d is not active.", student.getStudentId()));
        }
        student.addBarcodeRecord(barcodeRecord);
        personRepository.save(person);
        return barcodeRecord;
    }

    @Override
    public List<BarcodeRecord> getBarcodeRecordByStudentIdAndSessionId(Integer studentId, Integer sessionId) {
        return barcodeRecordRepository.getBarcodeRecordByStudentIdAndSessionId(studentId, sessionId);
    }

    public List<BarcodeRecord> getAll() {
        return barcodeRecordRepository.findAll();
    }
}
