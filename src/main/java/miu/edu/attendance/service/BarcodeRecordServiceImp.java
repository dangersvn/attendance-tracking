package miu.edu.attendance.service;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.Location;
import miu.edu.attendance.dto.BarcodeRecordDTO;
import miu.edu.attendance.repository.BarcodeRecordRepository;
import miu.edu.attendance.repository.ClassSessionRepository;
import miu.edu.attendance.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Transactional
public class BarcodeRecordServiceImp implements BarcodeRecordService{
    @Autowired
    BarcodeRecordRepository barcodeRecordRepository;

 @Autowired
    LocationRepository locationalRepository;

 @Autowired
    ClassSessionRepository classSessionRepository;

// The barcode reader creates a record of each scan by saving the bar-code ID, date, timeslot
//(morning or afternoon) and location.

    @Override
    public BarcodeRecord createBarcodeRecord(BarcodeRecordDTO barcodeRecordDTO) {
        BarcodeRecord barcodeRecord = new BarcodeRecord();
        barcodeRecord.setClassSession(classSessionRepository.findById(barcodeRecordDTO.getClassSessionId()).orElseThrow(() -> new EntityNotFoundException()));
        barcodeRecord.setLocation(locationalRepository.findById(barcodeRecordDTO.getLocationId()).orElseThrow(() -> new EntityNotFoundException()));
        barcodeRecord.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        return barcodeRecordRepository.save(barcodeRecord);


    }
}
