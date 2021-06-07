package miu.edu.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.service.CourseOfferingService;
import miu.edu.attendance.service.CourseService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseOfferingService courseOfferingService;
	
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}
}