package miu.edu.attendance.service;

import miu.edu.attendance.domain.BarcodeRecord;

import java.util.List;

public interface BarcodeRecordService {
    public List<BarcodeRecord> getBarcodeRecordByStudentIdAndSessionId(Integer studentId, Integer sessionId);
    public List<BarcodeRecord> getAll();

}
