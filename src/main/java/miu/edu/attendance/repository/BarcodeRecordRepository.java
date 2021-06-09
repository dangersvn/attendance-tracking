package miu.edu.attendance.repository;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.domain.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarcodeRecordRepository extends JpaRepository<BarcodeRecord, Integer> {

    @Query("select b from BarcodeRecord b, Student st where b.classSession.courseOffering.id = :courseOfferId and st.id = :studentId")
    List<BarcodeRecord> getBarcodeRecordByStudentIdAndCourseOfferId(@Param("studentId") Integer studentId, @Param("courseOfferId") Integer courseOfferId);

    @Query("select c from ClassSession c, Student st " +
            "join BarcodeRecord b on b.classSession.id = c.id and b.classSession.courseOffering.id = :courseOfferId " +
            "where st.id = :studentId")
    List<ClassSession> getAttendanceByStudentIdAndCourseOfferId(@Param("studentId") Integer studentId, @Param("courseOfferId") Integer courseOfferId);
}
