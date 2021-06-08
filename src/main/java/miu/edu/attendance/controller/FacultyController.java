package miu.edu.attendance.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.service.CourseOfferingService;
import miu.edu.attendance.service.CourseService;

@RestController
@RequestMapping("/faculty")
public class
FacultyController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseOfferingService courseOfferingService;
	
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		int faculty_id = 3;		//get faculty_id from access token
		return courseService.getAllCoursesByFacultyId(faculty_id);
	}
	
	@GetMapping("/courses/{course_id}/offerings")
	public List<CourseOffering> getAllCourseOfferingsByCourseId(@PathVariable int course_id) {
		//get the faculty_id from access token
		int faculty_id = 3;
		return courseOfferingService.getAllCourseOfferingsByFaculty(faculty_id, course_id);
	}
	
	@GetMapping("/offerings")
	public List<CourseOffering> getAllCourseOfferings() {
		int faculty_id = 2; //get the faculty_id from the access token
		return courseOfferingService.getAllCourseOfferingsByFaculty(faculty_id);
	}
}