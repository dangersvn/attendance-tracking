package miu.edu.attendance.service;

import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.Location;
import miu.edu.attendance.repository.ClassSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ClassSessionServiceImpl implements ClassSessionService {
    @Autowired
    ClassSessionRepository classSessionRepository;

    @Override
    public List<ClassSession> findByLocationAndDate(Location location, LocalDate date) {
        return classSessionRepository.findByLocationAndDate(location, date).orElseThrow(() -> new EntityNotFoundException(String.format("Not found classSession from Location=%s and Date:%s", location, date)));
    }
}