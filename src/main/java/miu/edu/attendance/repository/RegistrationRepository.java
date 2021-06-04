package miu.edu.attendance.repository;

import miu.edu.attendance.domain.Registration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RegistrationRepository extends CrudRepository<Registration, Integer> {
}
