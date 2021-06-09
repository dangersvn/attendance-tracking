package miu.edu.attendance.service;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.dto.BarcodeRecordDTO;
import miu.edu.attendance.dto.ClassSessionDTO;

import java.util.List;

public interface BarcodeRecordService {
    public List<BarcodeRecord> getBarcodeRecordByStudentIdAndCourseOfferId(Integer studentId, Integer courseOfferId);
    public List<BarcodeRecord> getAll();
    public BarcodeRecord createBarcodeRecord(BarcodeRecordDTO barcodeRecordDTO);
    public BarcodeRecord createBarcodeRecordToStudent(ClassSessionDTO classSessionDTO);
    public List<ClassSession> getBarcodeRecordAttendance(Integer studentId);

}
