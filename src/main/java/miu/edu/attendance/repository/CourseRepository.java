package miu.edu.attendance.repository;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CourseRepository extends CrudRepository<Course, Integer> {
    public List<Course> findAllByDescription(String Description);

//    List
}
