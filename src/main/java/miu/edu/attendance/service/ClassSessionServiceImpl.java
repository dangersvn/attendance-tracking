package miu.edu.attendance.service;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.Location;
import miu.edu.attendance.dto.StudentAttendanceDTO;
import miu.edu.attendance.repository.ClassSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class ClassSessionServiceImpl implements ClassSessionService {

    @Autowired
    private ClassSessionRepository classSessionRepository;

    @Autowired
    private BarcodeRecordService barcodeRecordService;

    @Override
    public List<ClassSession> findByLocationAndDate(Location location, LocalDate date) {
        return classSessionRepository.findByLocationAndDate(location, date).orElseThrow(() -> new EntityNotFoundException(String.format("Not found classSession from Location=%s and Date:%s", location, date)));
    }

    @Override
    public List<ClassSession> getClassSessionByCourseOfferingId(Integer courseOffId) {
        return classSessionRepository.getClassSessionByCourseOfferingId(courseOffId);
    }

    public List<StudentAttendanceDTO> attendanceStatus(Integer StudentId, Integer courseOffId){

        List<ClassSession> classSessionList = getClassSessionByCourseOfferingId(courseOffId);
        List<BarcodeRecord> barcodeRecordList = barcodeRecordService.getBarcodeRecordByStudentIdAndCourseOfferId(StudentId, courseOffId);
        List<StudentAttendanceDTO> sessionList = new LinkedList<>();

        boolean found;
        for (ClassSession classSession : classSessionList) {
            found = false;
            for (BarcodeRecord barcodeRecord : barcodeRecordList) {
                if (classSession.getId() == barcodeRecord.getClassSession().getId()) {
                    found = true;
                    break;
                }
            }
            if (found) {
                sessionList.add(new StudentAttendanceDTO(classSession.getDate(),
                        classSession.getTimeSlot().getBeginTime(), classSession.getTimeSlot().getEndTime(),
                        "Present", classSession.getTimeSlot().getAbbreviation()));
            } else {
                sessionList.add(new StudentAttendanceDTO(classSession.getDate(),
                        classSession.getTimeSlot().getBeginTime(), classSession.getTimeSlot().getEndTime(),
                        "Absent", classSession.getTimeSlot().getAbbreviation()));
            }
        }
        return sessionList;
    }
}