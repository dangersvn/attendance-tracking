package miu.edu.attendance.repository;

import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ClassSessionRepository extends JpaRepository<ClassSession, Integer> {
    Optional<List<ClassSession>> findByLocationAndDate(Location location, LocalDate date);
}
