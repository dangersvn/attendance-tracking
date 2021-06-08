package miu.edu.attendance.repository;

import miu.edu.attendance.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface PersonRepository extends CrudRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
    Optional<Person> findByBarcodeId(String barcodeId);
}
