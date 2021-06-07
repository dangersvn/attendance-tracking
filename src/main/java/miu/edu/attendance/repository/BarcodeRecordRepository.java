package miu.edu.attendance.repository;

import miu.edu.attendance.domain.BarcodeRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BarcodeRecordRepository extends CrudRepository<BarcodeRecord, Integer > {
}
