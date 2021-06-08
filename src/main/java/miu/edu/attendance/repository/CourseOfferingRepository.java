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

    @Query("select C from CourseOffering C, Student st , Registration R where C.startDate < current_date and C.endDate > current_date " )
    CourseOffering getAllCourseCurrent();

    @Query("select C from CourseOffering C, Student st , Registration R where C.endDate < current_date " )
    List<CourseOffering> getAllCoursePast();

    @Query("select C from CourseOffering C, Student st , Registration R where C.startDate > current_date " )
    List<CourseOffering> getAllCourseFuture();
    
    @Query("select f.courseOfferings from Faculty f where f.id = ?1")
    List<CourseOffering> getAllCourseOfferingsByFaculty(int faculty_id);    

}
