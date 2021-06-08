package miu.edu.attendance.service;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.dto.BarcodeRecordDTO;

import java.util.List;

public interface BarcodeRecordService {
    public List<BarcodeRecord> getBarcodeRecordByStudentIdAndSessionId(Integer studentId, Integer sessionId);
    public List<BarcodeRecord> getAll();
    BarcodeRecord createBarcodeRecord(BarcodeRecordDTO barcodeRecordDTO);

}
