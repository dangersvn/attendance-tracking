package miu.edu.attendance.repository;

import miu.edu.attendance.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StudentRepo extends CrudRepository<Student, Integer> {
}
