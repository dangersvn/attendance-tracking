package miu.edu.attendance.service;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.dto.BarcodeRecordDTO;

import java.util.List;

public interface BarcodeRecordService {
    List<BarcodeRecord> getAllByStudentId(Integer studentId);
    BarcodeRecord createBarcodeRecord(BarcodeRecordDTO barcodeRecordDTO);
}
