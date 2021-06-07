package miu.edu.attendance.controller;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CourseServiceImpl courseServiceImpl;

    @GetMapping("/courses")
    public List<Course> getAllCourse() {
        return courseServiceImpl.getAllcourses();
    }

  
}
