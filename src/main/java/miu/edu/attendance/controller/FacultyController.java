package miu.edu.attendance.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.security.SecurityUtils;
import miu.edu.attendance.service.BarcodeRecordService;
import miu.edu.attendance.service.ClassSessionService;
import miu.edu.attendance.service.CourseOfferingService;
import miu.edu.attendance.service.CourseService;
import miu.edu.attendance.service.StudentService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseOfferingService courseOfferingService;

	@Autowired
	private BarcodeRecordService barcodeRecordService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ClassSessionService classSessionService;

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

	@GetMapping("/offerings/{courseOffering_id}/attendance/{student_id}")
	public List<String> getAllClassSessionsAndAttendances(@PathVariable("courseOffering_id") Integer courseOfferId,
			@PathVariable("student_id") Integer studentId) {
		//control access to faculty only
		return classSessionService.attendanceStatus(studentId, courseOfferId);
	}

	@GetMapping("/offerings/{courseOffering_id}/attendance")
	public List<List<String>> getAttendanceforCourseOffering(@PathVariable int courseOffering_id) {
		List<List<String>> attendanceDetailForCourseOffering = new ArrayList<>();
		List<Student> students = studentService.getStudentsByCourseOffering(courseOffering_id);
		for(Student s: students) {
			List<String> attendanceStatusForOneStudent = classSessionService.attendanceStatus(Integer.parseInt(s.getStudentId()), courseOffering_id);
			attendanceDetailForCourseOffering.add(attendanceStatusForOneStudent);
		}
		return attendanceDetailForCourseOffering;
	}
}