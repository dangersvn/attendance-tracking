package miu.edu.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.security.SecurityUtils;
import miu.edu.attendance.service.BarcodeRecordService;
import miu.edu.attendance.service.CourseOfferingService;
import miu.edu.attendance.service.CourseService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseOfferingService courseOfferingService;
	
	@Autowired
	private BarcodeRecordService barcodeRecordService;

	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		Faculty faculty = SecurityUtils.getFaculty()
				.orElseThrow(() -> new IllegalStateException("Invalid access. Required faculty role."));
		return courseService.getAllCoursesByFacultyId(faculty.getId());
	}

	@GetMapping("/courses/{course_id}/offerings")
	public List<CourseOffering> getAllCourseOfferingsByCourseId(@PathVariable int course_id) {
		Faculty faculty = SecurityUtils.getFaculty()
				.orElseThrow(() -> new IllegalStateException("Invalid access. Required faculty role."));
		return courseOfferingService.getAllCourseOfferingsByFaculty(faculty.getId(), course_id);
	}

	@GetMapping("/offerings")
	public List<CourseOffering> getAllCourseOfferings() {
		Faculty faculty = SecurityUtils.getFaculty()
				.orElseThrow(() -> new IllegalStateException("Invalid access. Required faculty role."));
		return courseOfferingService.getAllCourseOfferingsByFaculty(faculty.getId());
	}

	@GetMapping("/offerings/{courseOffering_id}/attendance")
	public List<BarcodeRecord> getAttendanceforCourseOffering(@PathVariable int courseOffering_id) {
		Faculty faculty = SecurityUtils.getFaculty()
				.orElseThrow(() -> new IllegalStateException("Invalid access. Required faculty role."));
		List<CourseOffering> courseOfferings = courseOfferingService.getAllCourseOfferingsByFaculty(faculty.getId());
		return barcodeRecordService.getBarcodeRecordByStudentIdAndCourseOfferId(2, 2);
//		return null;
	}
}