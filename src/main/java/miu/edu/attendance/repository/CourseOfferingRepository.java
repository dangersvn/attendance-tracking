package miu.edu.attendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import miu.edu.attendance.domain.CourseOffering;


@Repository
@Transactional
public interface CourseOfferingRepository extends CrudRepository<CourseOffering, Integer> {

//    @Query("select C from CourseOffering C, Student st , Registration R where C.endDate < current_date " )
//    List<CourseOffering> getAllCourseOfferingsByStudentPast(String student_id);

    @Query("select C from CourseOffering C, Student st , Registration R where C.startDate > current_date " )
    List<CourseOffering> getAllCourseFuture();
    
    @Query("select f.courseOfferings from Faculty f where f.id = ?1")
    List<CourseOffering> getAllCourseOfferingsByFaculty(int faculty_id);

    @Query("select R.courseOffering from Registration R where R.student.studentId=?1")
    List<CourseOffering> getAllCourseOfferingsByStudent(String student_id);

    @Query("select C from CourseOffering C, Student st , Registration R where R.student.studentId=?1 and C.startDate<current_date and C.endDate>current_date ")
    CourseOffering getAllCourseOfferingsByStudentCurrent(String student_id);

    @Query("select C from CourseOffering C, Student st , Registration R where R.student.studentId=?1 and C.endDate < current_date ")
    List<CourseOffering> getAllCourseOfferingsByStudentpast(String student_id);
}
