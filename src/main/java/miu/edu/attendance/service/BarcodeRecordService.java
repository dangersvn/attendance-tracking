package miu.edu.attendance.service;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.dto.BarcodeRecordDTO;
import miu.edu.attendance.dto.ClassSessionDTO;

import java.util.List;
import java.util.Optional;

public interface BarcodeRecordService {
    public List<BarcodeRecord> getBarcodeRecordByStudentIdAndCourseOfferId(Integer studentId, Integer courseOfferId);
    public List<BarcodeRecord> getAll();
    public BarcodeRecord createBarcodeRecord(BarcodeRecordDTO barcodeRecordDTO);
    public BarcodeRecord createBarcodeRecordToStudent(ClassSessionDTO classSessionDTO);
    public List<ClassSession> getBarcodeRecordAttendance(Integer studentId);
    public Optional<BarcodeRecord> findByClassSessionAndStudent(Integer classSessionId, Integer studentId);
    public void deleteBarcodeRecord(Integer barcodeRecordId);
    public Optional<BarcodeRecord> findByBarcodeRecordId(Long barcodeRecordId);

}
