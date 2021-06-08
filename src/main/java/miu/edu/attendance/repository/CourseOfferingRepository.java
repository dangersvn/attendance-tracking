package miu.edu.attendance.repository;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.domain.CourseOffering;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CourseOfferingRepository extends CrudRepository<CourseOffering, Integer> {

    @Query("select C from CourseOffering C, Student st , Registration R where C.startDate < current_date and C.endDate > current_date " )
    CourseOffering getAllCourseCurrent();

    @Query("select C from CourseOffering C, Student st , Registration R where C.endDate < current_date " )
    List<CourseOffering> getAllCoursePast();

    @Query("select C from CourseOffering C, Student st , Registration R where C.startDate > current_date " )
    List<CourseOffering> getAllCourseFuture();
}
