package miu.edu.attendance.controller;

import miu.edu.attendance.domain.*;
import miu.edu.attendance.security.JwtUtil;
import miu.edu.attendance.security.SecurityUtils;
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

    //String student_id = "612345";		//get student_id from access token local


    @GetMapping("/assignedCourses")
        public List<CourseOffering> getAllCoursesByStudentId() {
        Student student = SecurityUtils.getStudent()
                .orElseThrow(() -> new IllegalStateException("Invalid access. Required Student role."));
        return courseOfferingService.getAllCourseOfferingsByStudent(student.getStudentId());
    }


    @GetMapping("/assignedCourses/current")
    public CourseOffering getAllCourseOfferingsByStudentCurrent() {
        Student student = SecurityUtils.getStudent()
                .orElseThrow(() -> new IllegalStateException("Invalid access. Required Student role."));
        return courseOfferingService.getAllCourseOfferingsByStudentCurrent(student.getStudentId());
    }

    @GetMapping("/assignedCourses/past")
    public List<CourseOffering> getAllCourseOfferingsByStudentpast() {
        Student student = SecurityUtils.getStudent()
                .orElseThrow(() -> new IllegalStateException("Invalid access. Required Student role."));
        return courseOfferingService.getAllCourseOfferingsByStudentpast(student.getStudentId());
    }

    @GetMapping("/assignedCourses/future")
    public List<CourseOffering> getAllCourseOfferingsByStudentfuture() {
        Student student = SecurityUtils.getStudent()
                .orElseThrow(() -> new IllegalStateException("Invalid access. Required Student role."));
        return courseOfferingService.getAllCourseOfferingsByStudentfuture(student.getStudentId());
    }


    @GetMapping("/allcourses")
    public Iterable<CourseOffering> getAllCourseOfferings() {
        return courseOfferingService.getAllCourseOfferings();
    }

    @GetMapping("/allcourses/past")
    public Iterable<CourseOffering> getAllCourseOfferingspast() {
        return courseOfferingService.getAllCourseOfferingspast();
    }


    @GetMapping("/allcourses/future")
    public Iterable<CourseOffering> getAllCourseOfferingsfuture() {
        return courseOfferingService.getAllCourseOfferingsfuture();
    }

    @GetMapping("/allcourses/{id}")
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
