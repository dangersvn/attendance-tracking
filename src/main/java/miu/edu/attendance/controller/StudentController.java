package miu.edu.attendance.controller;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.service.BarcodeRecordService;
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

    @Autowired
    BarcodeRecordService barcodeRecordService;


    @GetMapping("/courses")
    public List<CourseOffering> getAllCourseOfferings() {
        return courseOfferingService.getAllCourseOfferings();
    }

    @GetMapping("/courses/current")
    public CourseOffering getAllCourseCurrent() {
        return courseOfferingService.getAllCourseCurrent();
    }

    @GetMapping("/courses/past")
    public List<CourseOffering> getAllCoursePast() {
        return courseOfferingService.getAllCoursePast();
    }

    @GetMapping("/courses/future")
    public List<CourseOffering> getAllCourseFuture() {
        return courseOfferingService.getAllCourseFuture();
    }


    @GetMapping("/offerings/{id}")
    public Optional<CourseOffering> getAllCourseOfferingsById(@PathVariable int id) {
        return courseOfferingService.getAllCourseOfferings(id);
    }

    //implementation not finish
    @GetMapping("/report/attendance/courseoffering")
    public List<BarcodeRecord> getBarcodeRecordByStudentIdAndSessionId() {
        return barcodeRecordService.getBarcodeRecordByStudentIdAndCourseOfferId(1, 1);
    }

    //    @GetMapping("/offerings")
    //    public List<Course> getAllCourse() {
    //        return courseService.getAllCourses();
    //    }
  
}
