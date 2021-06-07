package miu.edu.attendance.repository;

import miu.edu.attendance.domain.BarcodeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarcodeRecordRepository extends JpaRepository<BarcodeRecord, Integer> {

    @Query("select b from BarcodeRecord b, Student s where s.studentId = :studentId")
    List<BarcodeRecord> getStudentsBy(@Param("studentId") Integer studentId);
}
