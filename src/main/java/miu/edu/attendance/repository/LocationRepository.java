package miu.edu.attendance.repository;

import miu.edu.attendance.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LocationRepository extends CrudRepository<Location, Integer> {
}
