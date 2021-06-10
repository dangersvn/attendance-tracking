package miu.edu.attendance.repository;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarcodeRecordRepository extends JpaRepository<BarcodeRecord, Long> {

    @Query("select b from BarcodeRecord b, Student st where b.classSession.courseOffering.id = :courseOfferId and b.student.id = :studentId")
    List<BarcodeRecord> getBarcodeRecordByStudentIdAndCourseOfferId(@Param("studentId") Integer studentId,
                                                                    @Param("courseOfferId") Integer courseOfferId);

    @Query("select distinct c from ClassSession c, Student st, BarcodeRecord b where st.id = :studentId")
    List<ClassSession> getBarcodeRecordAttendance(@Param("studentId") Integer studentId);

    Optional<BarcodeRecord> findByClassSessionAndStudent(ClassSession classSession, Student student);

    @Query("select b from BarcodeRecord b where  b.id = :barcodeRecordId")
    Optional<BarcodeRecord> findByBarcodeId(@Param("barcodeRecordId") Long barcodeRecordId);

}
