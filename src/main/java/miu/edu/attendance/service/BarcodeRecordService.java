package miu.edu.attendance.service;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.dto.BarcodeRecordDTO;

import java.util.List;

public interface BarcodeRecordService {
    public List<BarcodeRecord> getBarcodeRecordByStudentIdAndCourseOfferId(Integer studentId, Integer courseOfferId);
    public List<BarcodeRecord> getAll();
    public BarcodeRecord createBarcodeRecord(BarcodeRecordDTO barcodeRecordDTO);

}
