package miu.edu.attendance.controller;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.Person;
import miu.edu.attendance.dto.RegisterUserDto;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.service.CourseServiceImpl;
import miu.edu.attendance.service.PersonService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return courseServiceImpl.getAllCourses();
    }

  
}
