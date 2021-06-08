package miu.edu.attendance.service;

import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.Location;
import java.time.LocalDate;
import java.util.List;

public interface ClassSessionService {
    List<ClassSession> findByLocationAndDate(Location location, LocalDate date);
}
