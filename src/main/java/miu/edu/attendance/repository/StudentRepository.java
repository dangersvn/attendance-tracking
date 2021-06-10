package miu.edu.attendance.repository;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.domain.Person;
import miu.edu.attendance.domain.PersonRole;
import miu.edu.attendance.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("select distinct p from Person p, Student s where (p.id = s.id and  s.studentId = :keyword)" +
            " OR (p.firstName LIKE CONCAT('%',:keyword,'%') and p.id = s.id)" +
            " OR (p.lastName LIKE CONCAT('%',:keyword,'%') and p.id = s.id)"
    )
    List<Person> findByKeyword(@Param("keyword") String keyword);

    @Query("select r.student from Registration r where r.courseOffering.id = :courseOffering_id")
    List<Student> getStudentsByCourseOffering(@Param("courseOffering_id") int courseOffering_id);

}