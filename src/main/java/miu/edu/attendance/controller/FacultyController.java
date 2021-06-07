package miu.edu.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miu.edu.attendance.service.CourseOfferingService;
import miu.edu.attendance.service.CourseService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseOfferingService courseOfferingService;
}
