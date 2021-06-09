package miu.edu.attendance.service;

import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.Location;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ClassSessionService {
    List<ClassSession> findByLocationAndDate(Location location, LocalDate date);
    public List<ClassSession> getClassSessionByCourseOfferingId(Integer courseOffId);
    public List<String> attendanceStatus(Integer StudentId, Integer courseOffId);

}

