package miu.edu.attendance.service;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl  {
    @Autowired
    CourseRepository courseRepository;

    // @Override
    public List<Course> getAllcourses(){
        return (List<Course>) courseRepository.findAll();

    }

}
