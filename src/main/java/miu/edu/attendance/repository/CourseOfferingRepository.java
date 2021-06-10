package miu.edu.attendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import miu.edu.attendance.domain.CourseOffering;


@Repository
@Transactional
public interface CourseOfferingRepository extends CrudRepository<CourseOffering, Integer> {

        // For Faculty
        @Query("select f.courseOfferings from Faculty f where f.id = ?1")
        List<CourseOffering> getAllCourseOfferingsByFaculty(int faculty_id);


        // For Student


        @Query("select R.courseOffering from Registration R where R.student.studentId=?1")
        List<CourseOffering> getAllCourseOfferingsByStudent(String student_id);

        @Query("select R.courseOffering from Registration R where R.student.studentId=?1 and R.courseOffering.startDate<current_date and R.courseOffering.endDate>current_date ")
        CourseOffering getAllCourseOfferingsByStudentCurrent(String student_id);

        @Query("select R.courseOffering from Registration R where R.student.studentId=?1 and R.courseOffering.endDate < current_date ")
        List<CourseOffering> getAllCourseOfferingsByStudentpast(String student_id);

        @Query("select R.courseOffering from Registration R where R.student.studentId=?1 and R.courseOffering.startDate > current_date")
        List<CourseOffering> getAllCourseOfferingsByStudentfuture(String student_id);

        @Query("select distinct C from Registration R , CourseOffering C  where C.endDate< current_date " )
        List<CourseOffering> getAllCoursePast();

        @Query("select distinct C from Registration R , CourseOffering C where C.startDate > current_date " )
        List<CourseOffering> getAllCourseFuture();

        @Query("select distinct c from Registration r, CourseOffering c, Student st where st.id = :studentId")
        List<CourseOffering> getAllCourseOffering(@Param("studentId") Integer studentId);

}
