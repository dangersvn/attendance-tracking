package miu.edu.attendance.service;

import miu.edu.attendance.domain.*;
import miu.edu.attendance.dto.BarcodeRecordDTO;
import miu.edu.attendance.repository.BarcodeRecordRepository;
import miu.edu.attendance.repository.ClassSessionRepository;
import miu.edu.attendance.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

// The barcode reader creates a record of each scan by saving the bar-code ID, date, timeslot
//(morning or afternoon) and location.

    //@Override
    public BarcodeRecord createBarcodeRecord(BarcodeRecordDTO barcodeRecordDTO) {
        BarcodeRecord barcodeRecord = new BarcodeRecord();
        barcodeRecord.setClassSession(classSessionRepository.findById(barcodeRecordDTO.getClassSessionId()).get());
        barcodeRecord.setLocation(locationalRepository.findById(barcodeRecordDTO.getLocationId()).get());
        barcodeRecord.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
//        JPAUserDetails userDetails = SecurityUtils.getUserDetail();
//        Person person = personService.findByUsername(userDetails.getUsername());
//        Student student = person.asStudent();
//        student.getId();
        return barcodeRecordRepository.save(barcodeRecord);


    }

    @Override
    public List<BarcodeRecord> getBarcodeRecordByStudentIdAndSessionId(Integer studentId, Integer sessionId) {
        return barcodeRecordRepository.getBarcodeRecordByStudentIdAndSessionId(studentId, sessionId);
    }
    public List<BarcodeRecord> getAll(){
        return barcodeRecordRepository.findAll();
    }
}
