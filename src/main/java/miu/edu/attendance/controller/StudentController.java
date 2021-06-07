package miu.edu.attendance.controller;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.service.CourseOfferingService;
import miu.edu.attendance.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CourseService courseService;

    @Autowired
    private CourseOfferingService courseOfferingService;

    @GetMapping("/courses")
    public List<Course> getAllCourse() {
        return courseService.getAllCourses();
    }

    @GetMapping("/offerings")
    public List<CourseOffering> getAllCourseOfferings() {
        return courseOfferingService.getAllCourseOfferings();
    }

    @GetMapping("/offerings/{id}")
    public Optional<CourseOffering> getAllCourseOfferingsById(@PathVariable int id) {
        return courseOfferingService.getAllCourseOfferings(id);
    }

  
}
