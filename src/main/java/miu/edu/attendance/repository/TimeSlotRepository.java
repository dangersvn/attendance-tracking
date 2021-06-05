package miu.edu.attendance.repository;

import miu.edu.attendance.domain.TimeSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Integer> {
}
